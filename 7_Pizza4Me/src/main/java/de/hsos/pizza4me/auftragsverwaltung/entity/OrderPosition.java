package de.hsos.pizza4me.auftragsverwaltung.entity;

public class OrderPosition {

    private Long id;
    private String name;
    private Double price;
    private String size;
    private Long quantity;

    public OrderPosition() {

    }

    public OrderPosition(Long id, String name, Double price, String size, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
