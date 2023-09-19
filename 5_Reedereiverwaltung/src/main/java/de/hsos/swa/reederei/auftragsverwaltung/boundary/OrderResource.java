package de.hsos.swa.reederei.auftragsverwaltung.boundary;

import java.util.Collection;

import de.hsos.swa.reederei.auftragsverwaltung.control.ManageOrders;
import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/orders")
public class OrderResource {

    @Inject
    ManageOrders manageOrders;

    @GET
    public Response getAllOrders() {
        Collection<Order> orders = this.manageOrders.selectAllOrders();
        Collection<OrderDTO> dtos = orders.stream().map(this::mapOrderToDTO).toList();
        return Response.ok()
                .entity(dtos)
                .build();
    }

    @POST
    public Response postOrder(OrderDTO orderDTO) {
        Long orderId = this.manageOrders.createOrder(mapOrderDTOToOrder(orderDTO));
        if (orderId != null) {
            return Response.ok(orderId).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{orderid}")
    public Response getOrderDetails(@PathParam("orderid") Long orderId) {
        if(orderId != null) {
            Order order = this.manageOrders.selectOrder(orderId);
            return Response.ok(order).build();
        }
        
        return Response.noContent().build();
    }

    @PUT
    @Path("/{orderid}")
    public Response modifyOrder(@PathParam("orderid") Long orderId, OrderDTO orderDTO) {
        Order order = this.manageOrders.modifyOrder(mapOrderDTOToOrder(orderDTO));
        if (order != null) {
            return Response.ok(order).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{orderid}")
    public Response deleteOrder(@PathParam("orderid") Long orderId) {
        boolean deleted = this.manageOrders.deleteOrder(orderId);
        if (deleted) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    private OrderDTO mapOrderToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.orderId = order.getOrderId();
        dto.description = order.getDescription();
        dto.orderReceivementDate = order.getOrderReceivementDate();
        dto.shipUrl = order.getShipUrl();
        return dto;
    }

    private Order mapOrderDTOToOrder(OrderDTO orderDTO) {
        return new Order(orderDTO.orderId, orderDTO.description, orderDTO.orderReceivementDate, orderDTO.shipUrl);
    }
}
