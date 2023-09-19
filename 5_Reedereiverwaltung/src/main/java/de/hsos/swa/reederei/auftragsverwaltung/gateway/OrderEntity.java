package de.hsos.swa.reederei.auftragsverwaltung.gateway;

import de.hsos.swa.reederei.auftragsverwaltung.entity.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long orderId;

    @Version
    private Long version;

    private String description;
    private String orderReceivementDate;
    private String shipUrl;

    public static Order getOrderFromEntity(OrderEntity entity) {
        return new Order(entity.getOrderId(), entity.getDescription(), entity.getOrderReceivementDate(),
                entity.getShipUrl());
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderReceivementDate() {
        return orderReceivementDate;
    }

    public void setOrderReceivementDate(String orderReceivementDate) {
        this.orderReceivementDate = orderReceivementDate;
    }

    public String getShipUrl() {
        return shipUrl;
    }

    public void setShipUrl(String shipUrl) {
        this.shipUrl = shipUrl;
    }
}
