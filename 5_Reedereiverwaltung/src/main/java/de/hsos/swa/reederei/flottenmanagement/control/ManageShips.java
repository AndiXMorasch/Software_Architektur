package de.hsos.swa.reederei.flottenmanagement.control;

import java.util.Collection;

import de.hsos.swa.reederei.flottenmanagement.entity.Ship;

public interface ManageShips {
    Collection<Ship> selectAllShips();

    Ship selectShip(Long shipId);

    Long createShip(Ship ship);

    Ship modifyShip(Ship ship);
}
