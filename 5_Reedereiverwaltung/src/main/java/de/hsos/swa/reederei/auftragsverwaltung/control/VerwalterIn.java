package de.hsos.swa.reederei.auftragsverwaltung.control;

import java.util.Collection;

import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerwalterIn implements ManageOrders {

    @Inject
    OrderService orderService;

    @Override
    public Collection<Order> selectAllOrders() {
        return this.orderService.selectAllOrders();
    }

    @Override
    public Order selectOrder(Long orderId) {
        return this.orderService.selectOrder(orderId);
    }

    @Override
    public Long createOrder(Order order) {
        return this.orderService.createOrder(order);
    }

    @Override
    public Order modifyOrder(Order order) {
        return this.orderService.modifyOrder(order);
    }

    @Override
    public boolean deleteOrder(Long oderId) {
        return this.orderService.deleteOrder(oderId);
    }

}
