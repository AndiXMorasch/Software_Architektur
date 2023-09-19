package de.hsos.swa.reederei.flottenmanagement.control;

import java.util.Collection;
import java.util.Optional;

import de.hsos.swa.reederei.flottenmanagement.entity.Ship;
import de.hsos.swa.reederei.flottenmanagement.entity.ShipCatalog;
import de.hsos.swa.reederei.shared.events.NewOrderEvent;
import de.hsos.swa.reederei.shared.events.OrderAcceptedEvent;
import de.hsos.swa.reederei.shared.events.OrderDeletedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;

@ApplicationScoped
public class ShipService {
    @Inject
    private ShipCatalog shipCatalog;

    @Inject
    private Event<OrderAcceptedEvent> orderAcceptedEvent;

    public Collection<Ship> getAllShips() {
        return this.shipCatalog.selectAllShips();
    }

    public Long createShip(Ship ship) {
        return this.shipCatalog.createShip(ship);
    }

    public Ship selectShip(Long shipId) {
        return this.shipCatalog.selectShip(shipId);
    }

    public Ship modifyShip(Ship ship) {
        return this.shipCatalog.modifyShip(ship);
    }

    public void onNewOrder(@ObservesAsync NewOrderEvent event) {
        Optional<Ship> availableOptional = this.shipCatalog.selectAllShips().stream()
                .filter(Ship::isAvailable)
                .findFirst();
        if (availableOptional.isPresent()) {
            Ship available = availableOptional.get();
            available.setAvailable(false);
            shipCatalog.modifyShip(available);
            OrderAcceptedEvent e = new OrderAcceptedEvent();
            e.orderId = event.orderId;
            e.shipId = available.getId();
            orderAcceptedEvent.fireAsync(e);
        }
    }

    public void onOrderDeleted(@ObservesAsync OrderDeletedEvent event) {
        Ship ship = this.shipCatalog.selectShip(event.shipId);
        if (ship != null) {
            ship.setAvailable(true);
            this.shipCatalog.modifyShip(ship);
        }
    }
}
