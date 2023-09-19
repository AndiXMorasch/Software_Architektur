package de.hsos.swa.reederei.flottenmanagement.control;

import java.util.Collection;

import de.hsos.swa.reederei.flottenmanagement.entity.Ship;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Manager implements ManageShips {

    @Inject
    private ShipService shipService;

    @Override
    public Collection<Ship> selectAllShips() {
        return this.shipService.getAllShips();
    }

    @Override
    public Ship selectShip(Long shipId) {
        return this.shipService.selectShip(shipId);
    }

    @Override
    public Long createShip(Ship ship) {
        return this.shipService.createShip(ship);
    }

    @Override
    public Ship modifyShip(Ship ship) {
        return this.shipService.modifyShip(ship);
    }

}
