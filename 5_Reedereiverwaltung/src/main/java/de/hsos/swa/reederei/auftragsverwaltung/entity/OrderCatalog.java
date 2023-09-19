package de.hsos.swa.reederei.auftragsverwaltung.entity;

import java.util.Collection;

public interface OrderCatalog {
    Collection<Order> selectAllOrders();

    Order selectOrder(Long orderId);

    Long createOrder(Order order);

    Order modifyOrder(Order order);

    boolean deleteOrder(Long orderId);
}
