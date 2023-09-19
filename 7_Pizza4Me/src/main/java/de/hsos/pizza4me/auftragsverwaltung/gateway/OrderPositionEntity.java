package de.hsos.pizza4me.auftragsverwaltung.gateway;

import java.util.ArrayList;
import java.util.Collection;

import de.hsos.pizza4me.auftragsverwaltung.entity.OrderPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class OrderPositionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String name;
    private Double price;
    private String size;
    private Long quantity;

    public static Collection<OrderPosition> getOrderPositionsFromEntities(
            Collection<OrderPositionEntity> orderPositionsEntities) {
        Collection<OrderPosition> orderPositions = new ArrayList<>();
        if (orderPositionsEntities == null) {
            return orderPositions;
        }
        for (OrderPositionEntity o : orderPositionsEntities) {
            OrderPosition orderPos = new OrderPosition();
            orderPos.setId(o.getId());
            orderPos.setName(o.getName());
            orderPos.setPrice(o.getPrice());
            orderPos.setQuantity(o.getQuantity());
            orderPos.setSize(o.getSize());
            orderPositions.add(orderPos);
        }
        return orderPositions;
    }

    public static OrderPositionEntity getEntityFromOrderPosition(OrderPosition orderPosition) {
        OrderPositionEntity entity = new OrderPositionEntity();
        entity.setId(orderPosition.getId());
        entity.setName(orderPosition.getName());
        entity.setPrice(orderPosition.getPrice());
        entity.setQuantity(orderPosition.getQuantity());
        entity.setSize(orderPosition.getSize());
        return entity;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
