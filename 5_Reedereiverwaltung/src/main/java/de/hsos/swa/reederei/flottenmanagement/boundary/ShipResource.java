package de.hsos.swa.reederei.flottenmanagement.boundary;

import java.util.Collection;

import de.hsos.swa.reederei.flottenmanagement.control.ManageShips;
import de.hsos.swa.reederei.flottenmanagement.entity.Ship;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/ships")
public class ShipResource {

    @Inject
    private ManageShips shipManager;

    @GET
    public Response getAllShips() {
        Collection<Ship> ships = this.shipManager.selectAllShips();
        Collection<ShipDTO> dtos = ships.stream().map(this::mapShipToDTO).toList();
        return Response.ok()
                .entity(dtos)
                .build();
    }

    @POST
    public Response createShip(ShipDTO dto) {
        Ship ship = this.mapDTOToShip(dto);
        Long id = this.shipManager.createShip(ship);
        return Response.ok()
                .entity(id)
                .build();
    }

    @GET
    @Path("/{shipId}")
    public Response getShipById(@PathParam("shipId") Long shipId) {
        Ship ship = this.shipManager.selectShip(shipId);
        ShipDTO dto = this.mapShipToDTO(ship);
        return Response.ok()
                .entity(dto)
                .build();
    }

    @PUT
    @Path("/{shipId}")
    public Response modifyShip(@PathParam("shipId") Long shipId, ShipDTO dto) {
        if (!shipId.equals(dto.getId())) {
            return Response.status(400).entity("IDs have to match").build();
        }
        Ship ship = mapDTOToShip(dto);
        Ship modifiedShip = this.shipManager.modifyShip(ship);
        ShipDTO modified = this.mapShipToDTO(modifiedShip);
        return Response.ok()
                .entity(modified)
                .build();
    }

    private Ship mapDTOToShip(ShipDTO dto) {
        return new Ship(dto.getId(), dto.getName(), dto.isAvailable());
    }

    private ShipDTO mapShipToDTO(Ship ship) {
        ShipDTO dto = new ShipDTO();
        dto.setId(ship.getId());
        dto.setName(ship.getName());
        dto.setAvailable(ship.isAvailable());
        return dto;
    }
}
