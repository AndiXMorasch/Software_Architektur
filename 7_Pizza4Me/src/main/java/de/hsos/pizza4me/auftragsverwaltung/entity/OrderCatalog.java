package de.hsos.pizza4me.auftragsverwaltung.entity;

import java.util.Collection;

public interface OrderCatalog {
    Collection<Order> selectAllOrders();

    Order selectOrder(Long orderId);

    Collection<Order> selectAllCustomerOrders(String username);

    Long createOrder(String username); // 2 Möglichkeiten abgedeckt: 1) Parameter über Body 2) Parameter über PathParam

    Long addNewOrderPos(Long orderId, OrderPosition orderPosition); // returned index der neuen Bestellposition

    boolean changeOrderPos(Long orderId, Long index, OrderPosition orderPosition);

    boolean deleteOrderPos(Long orderId, Long index);

    boolean sendOrder(Long orderId);

    boolean deleteOrder(Long orderId);

    void deleteAllOrdersForCustomer(Long customerId);
}
