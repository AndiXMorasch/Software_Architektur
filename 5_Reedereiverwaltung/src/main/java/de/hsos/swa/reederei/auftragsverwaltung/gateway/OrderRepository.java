package de.hsos.swa.reederei.auftragsverwaltung.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.swa.reederei.auftragsverwaltung.entity.OrderCatalog;
import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderRepository implements OrderCatalog {

    @Inject
    private EntityManager em;

    @Override
    public Collection<Order> selectAllOrders() {
        TypedQuery<OrderEntity> findAll = em.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class);
        List<OrderEntity> enitites = findAll.getResultList();
        return enitites.stream()
                .map(OrderEntity::getOrderFromEntity)
                .toList();
    }

    @Override
    public Order selectOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order-ID cannot be null.");
        }
        OrderEntity entity = em.find(OrderEntity.class, orderId);
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + orderId + "' does not exist.");
        }
        return OrderEntity.getOrderFromEntity(entity);
    }

    @Override
    @Transactional
    public Long createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        OrderEntity entity = new OrderEntity();
        entity.setDescription(order.getDescription());
        entity.setOrderReceivementDate(order.getOrderReceivementDate());
        this.em.persist(entity);
        return entity.getOrderId();
    }

    @Override
    @Transactional
    public Order modifyOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        OrderEntity entity = em.find(OrderEntity.class, order.getOrderId());
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + order.getOrderId() + "' does not exist.");
        }
        entity.setDescription(order.getDescription());
        entity.setOrderReceivementDate(order.getOrderReceivementDate());
        entity.setShipUrl(order.getShipUrl());
        this.em.merge(entity);
        return order;
    }

    @Override
    @Transactional
    public boolean deleteOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order-ID cannot be null.");
        }

        OrderEntity orderEntity = em.find(OrderEntity.class, orderId);
        em.remove(orderEntity);
        return orderEntity != null;
    }

}
