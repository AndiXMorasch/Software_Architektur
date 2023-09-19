package de.hsos.swa.reederei.auftragsverwaltung.entity;

public class Order {
    private Long orderId;
    private String description;
    private String orderReceivementDate;
    private String shipUrl;

    public Order() {
    }

    public Order(Long orderId, String description, String orderReceivementDate, String shipUrl) {
        this.orderId = orderId;
        this.description = description;
        this.orderReceivementDate = orderReceivementDate;
        this.shipUrl = shipUrl;
    }

    public Long getOrderId() {
        return orderId;
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

    public Long extractShipIdFromUrl() {
        String shipId = this.shipUrl.substring(shipUrl.lastIndexOf("/") + 1);
        return Long.parseLong(shipId);
    }
}
