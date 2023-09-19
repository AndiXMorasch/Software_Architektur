package de.hsos.pizza4me.auftragsverwaltung.control;

import java.util.Collection;

import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderPosition;

public interface ManageOrders {
    Collection<Order> selectAllOrders();

    Order selectOrder(Long orderId);

    Collection<Order> selectAllCustomerOrders(String username);

    Long createOrder(String username); // 2 Möglichkeiten abgedeckt: 1) Parameter über Body 2) Parameter über PathParam

    Long addNewOrderPos(Long orderId, OrderPosition orderPosition, Long pizzaId); // returned index der neuen
                                                                                  // Bestellposition

    boolean changeOrderPos(Long oderId, Long index, OrderPosition orderPosition);

    boolean deleteOrderPos(Long orderId, Long index);

    boolean sendOrder(Long orderId);

    boolean deleteOrder(Long orderId);
}
