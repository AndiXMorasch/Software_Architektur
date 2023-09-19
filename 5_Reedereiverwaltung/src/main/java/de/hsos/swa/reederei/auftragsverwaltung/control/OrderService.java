package de.hsos.swa.reederei.auftragsverwaltung.control;

import java.util.Collection;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.net.URI;
import de.hsos.swa.reederei.auftragsverwaltung.entity.OrderCatalog;
import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;
import de.hsos.swa.reederei.shared.events.NewOrderEvent;
import de.hsos.swa.reederei.shared.events.OrderAcceptedEvent;
import de.hsos.swa.reederei.shared.events.OrderChangedEvent;
import de.hsos.swa.reederei.shared.events.OrderDeletedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriBuilder;

@ApplicationScoped
public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class);

    @ConfigProperty(name = "reederei.host.url", defaultValue = "http://localhost:8080/")
    private String hostUrl;

    @Inject
    OrderCatalog katalog;

    @Inject
    Event<NewOrderEvent> newOrderCreated;

    @Inject
    Event<OrderChangedEvent> orderChangedEvent;

    @Inject
    Event<OrderDeletedEvent> orderDeletedEvent;

    public Collection<Order> selectAllOrders() {
        return this.katalog.selectAllOrders();
    }

    public Order selectOrder(Long orderId) {
        return this.katalog.selectOrder(orderId);
    }

    public Long createOrder(Order order) {
        Long orderId = this.katalog.createOrder(order);

        if (orderId != null) {
            NewOrderEvent newOrder = new NewOrderEvent();
            newOrder.orderId = orderId;
            this.newOrderCreated.fireAsync(newOrder);
        }
        return orderId;
    }

    public Order modifyOrder(Order order) {
        Order orderTmp = this.katalog.modifyOrder(order);
        if (orderTmp != null) {
            OrderChangedEvent orderChanged = new OrderChangedEvent();
            this.orderChangedEvent.fireAsync(orderChanged);
        }
        return orderTmp;
    }

    public boolean deleteOrder(Long orderId) {
        Order order = this.selectOrder(orderId);
        boolean deleted = this.katalog.deleteOrder(orderId);
        if (deleted) {
            OrderDeletedEvent deletedOrder = new OrderDeletedEvent();
            deletedOrder.shipId = order.extractShipIdFromUrl();
            orderDeletedEvent.fireAsync(deletedOrder);
        }
        return deleted;
    }

    public void orderAccepted(@ObservesAsync OrderAcceptedEvent orderAccepted) {
        LOG.debug("Order Accepted Event: shipID=" + orderAccepted.shipId + " orderID=" + orderAccepted.orderId);
        Order order = this.selectOrder(orderAccepted.orderId);

        URI uri = UriBuilder.fromUri(this.hostUrl)
                .path("ships")
                .path(order.getOrderId().toString())
                .build();
        order.setShipUrl(uri.toString());

        this.modifyOrder(order);
    }
}
