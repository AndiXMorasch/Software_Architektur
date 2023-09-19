package de.hsos.pizza4me.auftragsverwaltung.entity;

import java.util.Collection;

public class Order {
    private Long id;
    private String username;
    private Collection<OrderPosition> orderPositions;
    private boolean status;

    public Order() {

    }

    public Order(Long orderId, String username, Collection<OrderPosition> orderPositions) {
        this.id = orderId;
        this.username = username;
        this.orderPositions = orderPositions;
        this.status = false; // initial immer false
    }

    public Order(Long orderId, String username, Collection<OrderPosition> orderPositions, boolean status) {
        this.id = orderId;
        this.username = username;
        this.orderPositions = orderPositions;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long orderId) {
        this.id = orderId;
    }

    public Collection<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Collection<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public boolean wasSended() {
        return status;
    }

    public void send() {
        this.status = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
