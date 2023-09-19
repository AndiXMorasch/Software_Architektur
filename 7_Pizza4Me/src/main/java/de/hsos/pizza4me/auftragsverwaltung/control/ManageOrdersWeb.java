package de.hsos.pizza4me.auftragsverwaltung.control;

import java.util.Collection;

import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaDTO;

public interface ManageOrdersWeb {
    Collection<Order> selectAllOrders();

    Order getOrderForCustomer(String username);

    Collection<PizzaDTO> selectAllPizzas();
}
