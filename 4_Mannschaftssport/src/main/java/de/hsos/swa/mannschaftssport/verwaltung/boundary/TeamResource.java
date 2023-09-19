package de.hsos.swa.mannschaftssport.verwaltung.boundary;

import java.net.URI;
import java.util.Arrays;
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
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.DataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.SingleDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.EntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.FullEntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.LinkDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.ListDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.TeamAttributeDTO;
import de.hsos.swa.mannschaftssport.verwaltung.control.ManageTeams;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;

@ApplicationScoped
@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    private ManageTeams teamManager;

    @Context
    UriInfo uriInfo;

    @Inject
    public TeamResource(ManageTeams teamManager) {
        this.teamManager = teamManager;
    }

    @GET
    public Response getAllTeams(@QueryParam("filter[name]") String name,
            @QueryParam("filter[category]") String category) {
        ListDataDTO data = new ListDataDTO();
        List<String> categories = category == null ? null : Arrays.asList(category.split(","));

        Collection<Team> teams = teamManager.selectAllTeams(name, categories);

        List<EntityDTO> entities = teams.stream()
                .map(t -> mapTeamToEntityDTO(t, true))
                .collect(Collectors.toList());
        data.setData(entities);
        return Response.ok()
                .entity(data)
                .build();
    }

    @POST
    public Response createTeam(SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("teams")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        Team team = this.mapEntityDTOToTeam(entity);
        Team newTeam = this.teamManager.createTeam(team);
        EntityDTO newTeamDTO = this.mapTeamToEntityDTO(newTeam, true);
        response.setData(newTeamDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/{teamID}")
    public Response getTeamById(@PathParam("teamID") Long teamId, @QueryParam("include") String include) {
        SingleDataDTO response = new SingleDataDTO();
        Team team = this.teamManager.selectTeam(teamId);
        if (team == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        FullEntityDTO dto = this.mapTeamToEntityDTO(team, false);
        response.setData(dto);

        if (include != null && include.equals("manager") && team.getManager() != null) {
            EntityDTO managerDTO = this.mapManagerToEntityDTO(team.getManager());
            response.setIncluded(List.of(managerDTO));
        }
        return Response.ok()
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{teamID}")
    public Response modifyTeam(@PathParam("teamID") Long teamId, SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("teams")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        if (!entity.getId().equals(teamId.toString())) { // Wieso ist die Id ein String?
            return Response.status(400, "ids stimmen nicht überein").build();
        }
        Team team = this.mapEntityDTOToTeam(entity);
        Team updatedTeam = this.teamManager.modifyTeam(team);
        if (updatedTeam == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        EntityDTO updatedTeamDTO = this.mapTeamToEntityDTO(updatedTeam, true);
        response.setData(updatedTeamDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{teamID}")
    public Response deleteTeamById(@PathParam("teamID") Long teamId) {
        boolean deleted = this.teamManager.deleteTeam(teamId);
        if (!deleted) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    @GET
    @Path("/{teamID}/manager")
    public Response getManagerFromTeam(@PathParam("teamID") Long teamId) {
        SingleDataDTO response = new SingleDataDTO();
        Manager manager = this.teamManager.getManagerOfTeam(teamId);
        if (manager == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        EntityDTO dto = this.mapManagerToEntityDTO(manager);
        response.setData(dto);
        return Response.ok()
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{teamID}/relationships/manager")
    public Response setManagerFromTeam(@PathParam("teamID") Long teamId, SingleDataDTO dto) {
        if (!dto.getData().getType().equals("manager")) {
            return Response.status(400, "Object has to be of type manager").build();
        }
        Long managerId = Long.parseLong(dto.getData().getId());
        boolean updated = this.teamManager.setManagerOfTeam(teamId, managerId);
        if (!updated) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{teamID}/relationships/manager")
    public Response deleteManagerFromTeam(@PathParam("teamID") Long teamId) {
        boolean deleted = this.teamManager.deleteManagerOfTeam(teamId);
        if (!deleted) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    @POST
    @Path("/{teamID}/relationships/players")
    public Response addPlayersToTeam(@PathParam("teamID") Long teamId, DataDTO dto) {
        List<EntityDTO> entities = dto.dataAsList();
        if (!entities.stream().allMatch(entity -> entity.getType().equals("player"))) {
            return Response.status(400, "All objects have to be of type player").build();
        }
        List<Long> playerIds = entities.stream().map(entity -> Long.parseLong(entity.getId())).toList();
        boolean added = this.teamManager.addPlayersToTeam(teamId, playerIds);
        if (!added) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    @GET
    @Path("/{teamID}/players")
    public Response getPlayersFromTeam(@PathParam("teamID") Long teamId) {
        ListDataDTO response = new ListDataDTO();
        Collection<Player> players = this.teamManager.listPlayersFromTeam(teamId);
        if (players == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        List<EntityDTO> entities = players.stream().map(this::mapPlayerToEntityDTO).toList();
        response.setData(entities);
        return Response.ok()
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{teamID}/relationships/players")
    public Response setPlayersOfTeam(@PathParam("teamID") Long teamId, DataDTO dto) {
        List<EntityDTO> entities = dto.dataAsList();
        if (!entities.stream().allMatch(entity -> entity.getType().equals("player"))) {
            return Response.status(400, "All objects have to be of type player").build();
        }
        List<Long> playerIds = entities.stream().map(entity -> Long.parseLong(entity.getId())).toList();
        boolean set = this.teamManager.modifyPlayersFromTeam(teamId, playerIds);
        if (!set) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{teamID}/relationships/players")
    public Response deletePlayersFromTeam(@PathParam("teamID") Long teamId, DataDTO dto) {
        List<EntityDTO> entities = dto.dataAsList();
        if (!entities.stream().allMatch(entity -> entity.getType().equals("player"))) {
            return Response.status(400, "All objects have to be of type player").build();
        }
        List<Long> playerIds = entities.stream().map(entity -> Long.parseLong(entity.getId())).toList();
        boolean deleted = this.teamManager.deletePlayerFromTeam(teamId, playerIds);
        if (!deleted) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    private FullEntityDTO mapManagerToEntityDTO(Manager manager) {
        FullEntityDTO dto = new FullEntityDTO();
        dto.setId(manager.getId().toString());
        dto.setType("manager");
        dto.setAttributes(AttributeDTO.getAttributesOfManager(manager));
        return dto;
    }

    private EntityDTO mapPlayerToEntityDTO(Player player) {
        FullEntityDTO dto = new FullEntityDTO();
        dto.setId(player.getId().toString());
        dto.setType("players");
        dto.setAttributes(AttributeDTO.getAttributesOfPlayer(player));
        return dto;
    }

    private FullEntityDTO mapTeamToEntityDTO(Team team, boolean includingLink) {
        FullEntityDTO dto = new FullEntityDTO();
        dto.setId(team.getId().toString());
        dto.setType("teams");
        dto.setAttributes(AttributeDTO.getAttributesOfTeam(team));
        if (includingLink) {
            dto.setLinks(LinkDTO.getSelfLinkDTO(this.getLinkForTeam(team)));
        }
        return dto;
    }

    private Team mapEntityDTOToTeam(FullEntityDTO dto) {
        Team team = new Team();
        if (dto.getId() != null) {
            team.setId(Long.parseLong(dto.getId()));
        }
        if (!(dto.getAttributes() instanceof TeamAttributeDTO)) {
            throw new IllegalStateException();
        }
        TeamAttributeDTO attributes = (TeamAttributeDTO) dto.getAttributes();
        team.setName(attributes.getName());
        team.setCategory(attributes.getCategory());
        return team;
    }

    private Link getLinkForTeam(Team team) {
        URI uri = UriBuilder.fromUri(uriInfo.getAbsolutePath())
                .path(team.getId().toString())
                .build();
        Link link = Link.fromUri(uri)
                .rel("team")
                .type(MediaType.APPLICATION_JSON)
                .param("method", "GET")
                .build();
        return link;
    }
}
