package de.hsos.pizza4me.auftragsverwaltung.control;

import java.util.Collection;
import java.util.Optional;

import de.hsos.pizza4me.auftragsverwaltung.acl.ManageOrdersForCustomers;
import de.hsos.pizza4me.auftragsverwaltung.acl.OrderInformationObtainer;
import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderCatalog;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderPosition;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService implements ManageOrders, ManageOrdersWeb, ManageOrdersForCustomers {

    @Inject
    OrderCatalog orderCatalog;

    @Inject
    OrderInformationObtainer orderInformationObtainer;

    @Override
    public Collection<Order> selectAllOrders() {
        return this.orderCatalog.selectAllOrders();
    }

    @Override
    public Order selectOrder(Long orderId) {
        return this.orderCatalog.selectOrder(orderId);
    }

    @Override
    public Collection<Order> selectAllCustomerOrders(String username) {
        return this.orderCatalog.selectAllCustomerOrders(username);
    }

    @Override
    public Long createOrder(String username) {
        return this.orderCatalog.createOrder(username);
    }

    @Override
    public Long addNewOrderPos(Long orderId, OrderPosition orderPosition, Long pizzaId) {
        PizzaDTO pizza = this.orderInformationObtainer.obtainPizzaInfo(pizzaId);
        orderPosition.setName(pizza.name);
        orderPosition.setPrice(pizza.price);
        return this.orderCatalog.addNewOrderPos(orderId, orderPosition);
    }

    @Override
    public boolean changeOrderPos(Long oderId, Long index, OrderPosition orderPosition) {
        return this.orderCatalog.changeOrderPos(oderId, index, orderPosition);
    }

    @Override
    public boolean deleteOrderPos(Long orderId, Long index) {
        return this.orderCatalog.deleteOrderPos(orderId, index);
    }

    @Override
    public boolean sendOrder(Long orderId) {
        return this.orderCatalog.sendOrder(orderId);
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        return this.orderCatalog.deleteOrder(orderId);
    }

    @Override
    public void deleteAllOrdersForCustomer(Long customerId) {
        this.orderCatalog.deleteAllOrdersForCustomer(customerId);
    }

    @Override
    public Collection<PizzaDTO> selectAllPizzas() {
        return this.orderInformationObtainer.obtainAllPizzas();
    }

    @Override
    public Order getOrderForCustomer(String username) {
        Optional<Order> optionalOrder = this.orderCatalog.selectAllCustomerOrders(username)
                .stream()
                .filter(order -> !order.wasSended()).findFirst();

        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        }

        Long newId = this.orderCatalog.createOrder(username);
        System.out.println(newId);
        return this.orderCatalog.selectOrder(newId);
    }

}
