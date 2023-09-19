package de.hsos.swa.reederei.flottenmanagement.entity;

public class Ship {
    private Long id;
    private String name;
    private boolean available;

    public Ship() {
    }

    public Ship(Long id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
