package de.hsos.swa.reederei.flottenmanagement.gateway;

import de.hsos.swa.reederei.flottenmanagement.entity.Ship;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class ShipEntity {

    public static Ship getShipFromEntity(ShipEntity entity) {
        return new Ship(entity.getId(), entity.getName(), entity.isAvailable());
    }

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String name;

    private boolean available;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
