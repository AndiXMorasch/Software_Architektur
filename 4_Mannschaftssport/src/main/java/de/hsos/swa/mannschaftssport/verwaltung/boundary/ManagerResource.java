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
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.ManagerAttributeDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.SingleDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.control.ManageManagers;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;

@ApplicationScoped
@Path("/managers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManagerResource {

    private ManageManagers manageManager;

    @Context
    UriInfo uriInfo;

    @Inject
    public ManagerResource(ManageManagers manageManager) {
        this.manageManager = manageManager;
    }

    @GET
    public Response getAllManagers(@QueryParam("filter[name]") String name) {
        ListDataDTO data = new ListDataDTO();

        Collection<Manager> managers = manageManager.selectAllManagers(name);

        List<EntityDTO> entities = managers.stream()
                .map(m -> mapManagerToEntityDTO(m, true))
                .collect(Collectors.toList());
        data.setData(entities);
        
        return Response.ok()
                .entity(data)
                .build();
    }

    @POST
    public Response createManager(SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("persons")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        Manager manager = this.mapEntityDTOToManager(entity);
        Manager newManager = this.manageManager.createManager(manager);
        EntityDTO newManagerDTO = this.mapManagerToEntityDTO(newManager, true);
        response.setData(newManagerDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/{managerID}")
    public Response getManagerById(@PathParam("managerID") Long managerId, @QueryParam("include") String include) {
        SingleDataDTO response = new SingleDataDTO();
        Manager manager = this.manageManager.selectManager(managerId);
        if (manager == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        FullEntityDTO dto = this.mapManagerToEntityDTO(manager, false);
        response.setData(dto);

        return Response.ok()
                .entity(response)
                .build();
    }

    @PUT
    @Path("/{managerID}")
    public Response modifyManager(@PathParam("managerID") Long managerId, SingleDataDTO dto) {
        if (!(dto.getData() instanceof FullEntityDTO)) {
            return Response.status(400, "fehlende attribute").build();
        }
        SingleDataDTO response = new SingleDataDTO();
        FullEntityDTO entity = (FullEntityDTO) dto.getData();
        if (!entity.getType().equals("persons")) {
            return Response.status(400, "fehlerhafter typ").build();
        }
        if (!entity.getId().equals(managerId.toString())) {
            return Response.status(400, "ids stimmen nicht überein").build();
        }
        Manager manager = this.mapEntityDTOToManager(entity);
        Manager updatedManager = this.manageManager.modifyManager(manager);
        if (updatedManager == null) {
            return Response.status(404, "Object with given ID not found").build();
        }
        EntityDTO updatedManagerDTO = this.mapManagerToEntityDTO(updatedManager, true);
        response.setData(updatedManagerDTO);
        return Response.ok()
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/{managerID}")
    public Response deleteManagerById(@PathParam("managerID") Long managerId) {
        boolean deleted = this.manageManager.deleteManager(managerId);
        if (!deleted) {
            return Response.status(404, "Object with given ID not found").build();
        }
        return Response.ok()
                .build();
    }

    private FullEntityDTO mapManagerToEntityDTO(Manager manager, boolean includingLink) {
        FullEntityDTO dto = new FullEntityDTO();
        dto.setId(manager.getId().toString());
        dto.setType("persons");
        dto.setAttributes(AttributeDTO.getAttributesOfManager(manager));
        if (includingLink) {
            dto.setLinks(LinkDTO.getSelfLinkDTO(this.getLinkForManager(manager)));
        }
        return dto;
    }

    private Link getLinkForManager(Manager manager) {
        URI uri = UriBuilder.fromUri(uriInfo.getAbsolutePath())
                .path(manager.getId().toString())
                .build();
        Link link = Link.fromUri(uri)
                .rel("persons")
                .type(MediaType.APPLICATION_JSON)
                .param("method", "GET")
                .build();
        return link;
    }

    private Manager mapEntityDTOToManager(FullEntityDTO dto) {
        Manager manager = new Manager();
        if (dto.getId() != null) {
            manager.setId(Long.parseLong(dto.getId()));
        }
        if (!(dto.getAttributes() instanceof ManagerAttributeDTO)) {
            throw new IllegalStateException();
        }
        ManagerAttributeDTO attributes = (ManagerAttributeDTO) dto.getAttributes();
        manager.setName(attributes.getName());
        return manager;
    }
}
