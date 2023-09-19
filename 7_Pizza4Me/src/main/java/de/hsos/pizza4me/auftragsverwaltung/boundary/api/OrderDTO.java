package de.hsos.pizza4me.auftragsverwaltung.boundary.api;

import java.util.Collection;

public class OrderDTO {

    public Long orderId;
    public String username;
    public Collection<OrderPositionDTO> orderPositions;
    public boolean status;
}
