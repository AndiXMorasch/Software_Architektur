package de.hsos.swa.reederei.flottenmanagement.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.swa.reederei.flottenmanagement.entity.Ship;
import de.hsos.swa.reederei.flottenmanagement.entity.ShipCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ShipRepository implements ShipCatalog {

    @Inject
    private EntityManager em;

    @Override
    public Collection<Ship> selectAllShips() {
        TypedQuery<ShipEntity> findAll = em.createQuery("SELECT s FROM ShipEntity s", ShipEntity.class);
        List<ShipEntity> enitites = findAll.getResultList();
        return enitites.stream()
                .map(ShipEntity::getShipFromEntity)
                .toList();
    }

    @Override
    public Ship selectShip(Long shipId) {
        if (shipId == null) {
            throw new IllegalArgumentException("Ship-ID cannot be null.");
        }
        ShipEntity entity = em.find(ShipEntity.class, shipId);
        if (entity == null) {
            throw new NotFoundException("Ship with ID: '" + shipId + "' does not exist.");
        }
        return ShipEntity.getShipFromEntity(entity);
    }

    @Override
    @Transactional
    public Long createShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException("Ship cannot be null.");
        }
        ShipEntity entity = new ShipEntity();
        entity.setName(ship.getName());
        entity.setAvailable(true);
        this.em.persist(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public Ship modifyShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException("Ship cannot be null.");
        }
        ShipEntity entity = em.find(ShipEntity.class, ship.getId());
        if (entity == null) {
            throw new NotFoundException("Ship with ID: '" + ship.getId() + "' does not exist.");
        }
        entity.setName(ship.getName());
        entity.setAvailable(ship.isAvailable());
        this.em.merge(entity);
        return ship;
    }

}
