package de.hsos.swa.mannschaftssport.verwaltung.boundary;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.AttributeDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.EntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.FullEntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.LinkDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.ListDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.PlayerAttributeDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.SingleDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.control.ManagePlayers;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;

@ApplicationScoped
@Path("/players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    private ManagePlayers managePlayers;

    @Context
    UriInfo uriInfo;

    @Inject
    public PlayerResource(ManagePlayers managePlayers) {
        this.managePlayers = managePlayers;
    }

    @GET
    public Response getAllPlayers(@QueryParam("filter[name]") String name) {
        ListDataDTO data = new ListDataDTO();

        Collection<Player> players = managePlayers.selectAllPlayers(name);

        List<EntityDTO> entities = players.stream()
                .map(p -> mapPlayerToEntityDTO(p, true))
                .collect(Collectors.toList());
        data.setData(entities);
        
        return Response.ok()
                .entity(data)
                .build();
    }

    @POST
    public Response createPlayer(SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("persons")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        Player player = this.mapEntityDTOToPlayer(entity);
        Player newPlayer = this.managePlayers.createPlayer(player);
        EntityDTO newPlayerDTO = this.mapPlayerToEntityDTO(newPlayer, true);
        response.setData(newPlayerDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/{playerID}")
    public Response getPlayerById(@PathParam("playerID") Long playerId, @QueryParam("include") String include) {
        SingleDataDTO response = new SingleDataDTO();
        Player player = this.managePlayers.selectPlayer(playerId);
        if (player == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        FullEntityDTO dto = this.mapPlayerToEntityDTO(player, false);
        response.setData(dto);

        return Response.ok()
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{playerID}")
    public Response modifyPlayer(@PathParam("playerID") Long playerId, SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("persons")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        if (!entity.getId().equals(playerId.toString())) {
            return Response.status(400, "ids stimmen nicht überein").build();
        }
        Player player = this.mapEntityDTOToPlayer(entity);
        Player updatedPlayer = this.managePlayers.modifyPlayer(player);
        if (updatedPlayer == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        EntityDTO updatedPlayerDTO = this.mapPlayerToEntityDTO(updatedPlayer, true);
        response.setData(updatedPlayerDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{playerID}")
    public Response deletePlayerById(@PathParam("playerID") Long playerId) {
        boolean deleted = this.managePlayers.deletePlayer(playerId);
        if (!deleted) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    private FullEntityDTO mapPlayerToEntityDTO(Player player, boolean includingLink) {
        FullEntityDTO dto = new FullEntityDTO();
        dto.setId(player.getId().toString());
        dto.setType("persons");
        dto.setAttributes(AttributeDTO.getAttributesOfPlayer(player));
        if (includingLink) {
            dto.setLinks(LinkDTO.getSelfLinkDTO(this.getLinkForPlayer(player)));
        }
        return dto;
    }

    private Link getLinkForPlayer(Player player) {
        URI uri = UriBuilder.fromUri(uriInfo.getAbsolutePath())
                .path(player.getId().toString())
                .build();
        Link link = Link.fromUri(uri)
                .rel("persons")
                .type(MediaType.APPLICATION_JSON)
                .param("method", "GET")
                .build();
        return link;
    }

    private Player mapEntityDTOToPlayer(FullEntityDTO dto) {
        Player player = new Player();
        if (dto.getId() != null) {
            player.setId(Long.parseLong(dto.getId()));
        }
        if (!(dto.getAttributes() instanceof PlayerAttributeDTO)) {
            throw new IllegalStateException();
        }
        PlayerAttributeDTO attributes = (PlayerAttributeDTO) dto.getAttributes();
        player.setName(attributes.getName());
        player.setCondition(attributes.getCondition());
        return player;
    }
}
