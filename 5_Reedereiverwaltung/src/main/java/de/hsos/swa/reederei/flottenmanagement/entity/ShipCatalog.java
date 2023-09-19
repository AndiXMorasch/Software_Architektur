package de.hsos.swa.reederei.flottenmanagement.entity;

import java.util.Collection;

public interface ShipCatalog {
    Collection<Ship> selectAllShips();

    Ship selectShip(Long shipId);

    Long createShip(Ship ship);

    Ship modifyShip(Ship ship);
}
