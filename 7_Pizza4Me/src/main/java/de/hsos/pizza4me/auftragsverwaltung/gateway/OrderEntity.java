package de.hsos.pizza4me.auftragsverwaltung.gateway;

import java.util.List;

import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import static java.lang.Math.toIntExact;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String username;
    private boolean status;

    @OneToMany
    private List<OrderPositionEntity> orderPositions;

    public static Order getOrderFromEntity(OrderEntity entity) {
        return new Order(entity.getId(), entity.getUsername(),
                OrderPositionEntity.getOrderPositionsFromEntities(entity.getOrderPositions()), entity.wasSended());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<OrderPositionEntity> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionEntity> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public void addOrderPos(OrderPositionEntity orderPos) {
        this.orderPositions.add(orderPos);
    }

    public void changeOrderPos(Long index, OrderPositionEntity newOrderPos) {
        int i = toIntExact(index);
        this.orderPositions.set(i, newOrderPos);
    }

    public boolean deleteOrderPos(Long index) {
        int oldSize = this.orderPositions.size();
        int i = toIntExact(index);
        this.orderPositions.remove(i);
        return oldSize > orderPositions.size();
    }

    public void setSended(boolean sended) {
        this.status = sended;
    }

    public boolean wasSended() {
        return this.status;
    }

    public boolean send() {
        if (!status) {
            this.status = true;
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
