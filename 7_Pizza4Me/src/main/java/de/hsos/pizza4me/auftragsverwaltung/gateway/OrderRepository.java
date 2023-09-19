package de.hsos.pizza4me.auftragsverwaltung.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderCatalog;
import de.hsos.pizza4me.auftragsverwaltung.entity.OrderPosition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.ws.rs.NotFoundException;

@Transactional(TxType.MANDATORY)
@ApplicationScoped
public class OrderRepository implements OrderCatalog {

    @Inject
    private EntityManager em;

    @Override
    public Collection<Order> selectAllOrders() {
        TypedQuery<OrderEntity> findAll = em.createQuery("SELECT o FROM OrderEntity o", OrderEntity.class);
        List<OrderEntity> entities = findAll.getResultList();
        return entities.stream()
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
    public Collection<Order> selectAllCustomerOrders(String username) {
        TypedQuery<OrderEntity> findAll = em.createQuery("SELECT o FROM OrderEntity o WHERE o.username = :username",
                OrderEntity.class);
        findAll.setParameter("username", username);
        List<OrderEntity> entities = findAll.getResultList();

        return entities.stream()
                .map(OrderEntity::getOrderFromEntity)
                .toList();
    }

    @Override
    public Long createOrder(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null.");
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUsername(username);
        orderEntity.setSended(false);

        this.em.persist(orderEntity);
        return orderEntity.getId();
    }

    @Override
    public Long addNewOrderPos(Long orderId, OrderPosition orderPosition) {
        if (!requestIsCorrect(orderPosition)) {
            throw new IllegalArgumentException("Some of the order positions data is missing.");
        }

        OrderEntity entity = em.find(OrderEntity.class, orderId);
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + orderId + "' does not exist.");
        }

        OrderPositionEntity orderPosEntity = OrderPositionEntity.getEntityFromOrderPosition(orderPosition);
        this.em.persist(orderPosEntity);
        entity.addOrderPos(orderPosEntity);
        this.em.merge(entity);
        return orderPosEntity.getId();
    }

    @Override
    public boolean changeOrderPos(Long orderId, Long index, OrderPosition orderPosition) {
        if (!requestIsCorrect(orderPosition)) {
            throw new IllegalArgumentException("Some of the order positions data is missing.");
        }

        if (index == null || orderId == null) {
            throw new IllegalArgumentException("Index and OrderID cannot be null.");
        }

        OrderEntity entity = em.find(OrderEntity.class, orderId);
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + orderId + "' does not exist.");
        }

        entity.changeOrderPos(index, OrderPositionEntity.getEntityFromOrderPosition(orderPosition));
        return true;
    }

    @Override
    public boolean deleteOrderPos(Long orderId, Long index) {
        if (index == null || orderId == null) {
            throw new IllegalArgumentException("Index and OrderID cannot be null.");
        }

        OrderEntity entity = em.find(OrderEntity.class, orderId);
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + orderId + "' does not exist.");
        }

        boolean deleted = entity.deleteOrderPos(index);
        em.merge(entity);
        return deleted;
    }

    @Override
    public boolean sendOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("OrderID cannot be null.");
        }

        OrderEntity entity = em.find(OrderEntity.class, orderId);
        if (entity == null) {
            throw new NotFoundException("Order with ID: '" + orderId + "' does not exist.");
        }

        boolean sended = entity.send();
        em.merge(entity);
        return sended;
    }

    @Override
    public boolean deleteOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order-ID cannot be null.");
        }

        OrderEntity orderEntity = em.find(OrderEntity.class, orderId);
        em.remove(orderEntity);
        return orderEntity != null;
    }

    private boolean requestIsCorrect(OrderPosition orderPos) {
        if (orderPos == null) {
            return false;
        }

        if (orderPos.getId() == null || orderPos.getQuantity() == null || orderPos.getSize().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public void deleteAllOrdersForCustomer(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer-ID cannot be null.");
        }

        TypedQuery<OrderEntity> deleteAll = em
                .createQuery("DELETE o FROM OrderEntity o WHERE o.customerId = :customerId", OrderEntity.class);
        deleteAll.setParameter("customerId", customerId);
        deleteAll.executeUpdate();
    }
}
