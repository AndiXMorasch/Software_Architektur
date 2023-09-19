package de.hsos.pizza4me.auftragsverwaltung.boundary.api;

import java.util.ArrayList;
import java.util.Collection;

import de.hsos.pizza4me.auftragsverwaltung.control.ManageOrders;
import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderPosition;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response.Status;
import jakarta.transaction.Transactional.TxType;

@RequestScoped
@Transactional(TxType.REQUIRES_NEW)
@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class OrderResource {

    @Inject
    ManageOrders manageOrders;

    @Context
    SecurityContext context;

    @GET
    @RolesAllowed("admin")
    public Response getAllOrders() {
        Collection<Order> orders = this.manageOrders.selectAllOrders();
        Collection<OrderDTO> dtos = orders.stream().map(this::mapOrderToDTO).toList();
        return Response.ok()
                .entity(dtos)
                .build();
    }

    @POST
    public Response createOrder() {
        String username = this.context.getUserPrincipal().getName();
        Long orderId = this.manageOrders.createOrder(username);
        if (orderId != null) {
            return Response.ok(orderId).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/customer")
    public Response getOrdersByCustomer() {
        String username = this.context.getUserPrincipal().getName();

        Collection<Order> orders = this.manageOrders.selectAllCustomerOrders(username);
        Collection<OrderDTO> dtos = orders.stream().map(this::mapOrderToDTO).toList();
        return Response.ok(dtos).build();
    }

    @GET
    @RolesAllowed("admin")
    @Path("/customer/{username}")
    public Response getOrdersByCustomer(@PathParam("username") String username) {
        if (username != null) {
            Collection<Order> orders = this.manageOrders.selectAllCustomerOrders(username);
            Collection<OrderDTO> dtos = orders.stream().map(this::mapOrderToDTO).toList();
            return Response.ok(dtos).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @RolesAllowed("admin")
    @Path("/customer/{username}")
    public Response createOrder(@PathParam("username") String username) {
        Long orderId = this.manageOrders.createOrder(username);
        if (orderId != null) {
            return Response.ok(orderId).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{orderid}")
    public Response getOrderDetails(@PathParam("orderid") Long orderId) {
        Order order = this.manageOrders.selectOrder(orderId);
        if (order != null) {
            OrderDTO dto = mapOrderToDTO(order);
            return Response.ok(dto).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{orderid}")
    public Response sendOrder(@PathParam("orderid") Long orderId) {
        Order order = this.manageOrders.selectOrder(orderId);
        if (order == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        boolean alreadyBeenSended = order.wasSended();
        boolean justSended = this.manageOrders.sendOrder(orderId);
        if (alreadyBeenSended || !justSended) {
            return Response.status(Status.FORBIDDEN).build();
        }

        return Response.noContent().build();
    }

    @POST
    @Path("/{orderid}")
    public Response addNewOrderPos(@PathParam("orderid") Long orderId, @Valid NewOrderPositionDTO orderPos) {
        Order order = this.manageOrders.selectOrder(orderId);
        if (order == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        OrderPosition pos = mapOrderPosDTOToOrderPos(orderPos);
        Long index = this.manageOrders.addNewOrderPos(orderId, pos, orderPos.pizzaId);
        if (index == null) {
            return Response.status(Status.FORBIDDEN).build();
        }

        return Response.ok(index).build();
    }

    @DELETE
    @Path("/{orderid}")
    public Response deleteOrder(@PathParam("orderid") Long orderId) {
        boolean deleted = this.manageOrders.deleteOrder(orderId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{orderid}/{index}")
    public Response changeOrderPos(@PathParam("orderid") Long orderId, @PathParam("index") Long index,
            @Valid NewOrderPositionDTO orderPosDTO) {
        Order order = this.manageOrders.selectOrder(orderId);
        if (order == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (order.wasSended()) {
            return Response.status(Status.FORBIDDEN).build();
        }

        OrderPosition orderPos = mapOrderPosDTOToOrderPos(orderPosDTO);
        boolean changed = this.manageOrders.changeOrderPos(orderId, index, orderPos);

        if (!changed) {
            return Response.status(Status.FORBIDDEN).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{orderid}/{index}")
    public Response changeOrderPos(@PathParam("orderid") Long orderId, @PathParam("index") Long index) {
        Order order = this.manageOrders.selectOrder(orderId);
        if (order == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        if (order.wasSended()) {
            return Response.status(Status.FORBIDDEN).build();
        }

        boolean deleted = this.manageOrders.deleteOrderPos(orderId, index);
        if (!deleted) {
            return Response.status(Status.FORBIDDEN).build();
        }

        return Response.noContent().build();
    }

    private OrderDTO mapOrderToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.username = order.getUsername();
        dto.orderId = order.getId();
        dto.orderPositions = this.mapOrderPosToDTO(order.getOrderPositions());
        dto.status = order.wasSended();
        return dto;
    }

    private Collection<OrderPositionDTO> mapOrderPosToDTO(Collection<OrderPosition> positions) {
        ArrayList<OrderPositionDTO> orderPosDTOs = new ArrayList<>();
        for (OrderPosition o : positions) {
            OrderPositionDTO orderPos = new OrderPositionDTO();
            orderPos.name = o.getName();
            orderPos.price = o.getPrice();
            orderPos.quantity = o.getQuantity();
            orderPos.size = o.getSize();
            orderPosDTOs.add(orderPos);
        }
        return orderPosDTOs;
    }

    private OrderPosition mapOrderPosDTOToOrderPos(NewOrderPositionDTO dto) {
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setId(dto.pizzaId);
        orderPosition.setSize(dto.size);
        orderPosition.setQuantity(dto.quantity);
        return orderPosition;
    }
}
