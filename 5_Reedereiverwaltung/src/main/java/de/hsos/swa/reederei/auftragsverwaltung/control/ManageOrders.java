package de.hsos.swa.reederei.auftragsverwaltung.control;

import java.util.Collection;

import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;

public interface ManageOrders {
    Collection<Order> selectAllOrders();

    Order selectOrder(Long orderId);

    Long createOrder(Order order);

    Order modifyOrder(Order order);

    boolean deleteOrder(Long oderId);
}
