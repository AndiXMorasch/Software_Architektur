package de.hsos.swa.mocktail.verwalten.boundary.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import de.hsos.swa.mocktail.verwalten.control.ManageMocktails;
import de.hsos.swa.mocktail.verwalten.entity.Einheit;
import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

@Path("/mocktails")
public class MocktailRessource {

    private static final Logger LOG = Logger.getLogger(MocktailRessource.class);

    @Inject
    ManageMocktails manageMocktails;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Schema(implementation = ListMocktailDTO.class)
    @Fallback(fallbackMethod = "fallbackGetMocktails")
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - mocktail list returned")
    public Response getMocktails() {
        LOG.info("Get all");
        List<ListMocktailDTO> mocktailList = mapMocktailToListMocktailDTO(manageMocktails.selectAllMocktails());
        LOG.info(mocktailList);
        return Response.ok(mocktailList).build();
    }

    public Response fallbackGetMocktails() {
        LOG.info("Fallback Method called - Getting cocktail by id '1'");
        List<Mocktail> mocktailList = new ArrayList<>();
        mocktailList.add(manageMocktails.selectMocktail(1));

        List<ListMocktailDTO> listMocktailDTOs = this.mapMocktailToListMocktailDTO(mocktailList);
        LOG.trace(listMocktailDTOs);
        return Response.ok(listMocktailDTOs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 4)
    @Timeout(250)
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - mocktail created")
    public Response postMocktail(CreateMocktailDTO createMocktailDTO) {
        LOG.info("Create Mocktail" + createMocktailDTO);
        Long id = manageMocktails
                .createMocktail(new Mocktail(null, createMocktailDTO.name, createMocktailDTO.beschreibung, null));
        LOG.trace("Created with ID: '" + id + "'");
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{mocktailid}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackGetMocktailDetails")
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - details for mocktail returned")
    public Response getMocktailDetails(@PathParam("mocktailid") long mocktailid) {
        LOG.info("Get mocktail by id '" + mocktailid + "'");
        Mocktail mocktail = manageMocktails.selectMocktail(mocktailid);
        if (mocktail != null) {
            return Response.ok(mocktail).build();
        }
        LOG.trace(mocktail);
        return Response.noContent().build();
    }

    public Response fallbackGetMocktailDetails(@PathParam("mocktailid") long mocktailid) {
        LOG.warn("Fallback Method called - Returning dummy mocktail");
        List<Zutat> zutaten = new ArrayList<>();
        Zutat zutat = new Zutat("Zitrone", 0.5f, Einheit.MILLILITER);
        zutaten.add(zutat);
        Mocktail dummyMocktail = new Mocktail(-1l, "Shirley Temple", "Fruchtiger Zitronengeschmack", zutaten);
        return Response.ok(dummyMocktail).build();
    }

    @PUT
    @Path("/{mocktailid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 4)
    @Timeout(250)
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - mocktail changed successfully")
    public Response modifyMocktail(@PathParam("mocktailid") long mocktailid, ModifyMocktailDTO modifyMocktailDTO) {
        LOG.info("Change mocktail with id '" + mocktailid + "'");
        if (manageMocktails.selectMocktail(mocktailid) != null) {
            Mocktail mocktail = new Mocktail(mocktailid, modifyMocktailDTO.name, modifyMocktailDTO.beschreibung, null);
            LOG.trace(mocktail);
            manageMocktails.applyModification(mocktail);
            return Response.ok(mocktail).build();
        }
        LOG.warn("Mocktail with ID: '" + mocktailid + "' not found");
        return Response.noContent().build();
    }

    @POST
    @Path("/{mocktailid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 4)
    @Timeout(250)
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - ingredient added successfully")
    public Response addMocktailIngredient(@PathParam("mocktailid") long mocktailid, DetailZutatDTO detailZutatDTO) {
        LOG.info("Add ingredient " + detailZutatDTO.name + " to mocktail with id '" + mocktailid + "'");
        if (manageMocktails.selectMocktail(mocktailid) != null) {
            Zutat zutat = mapDetailZutatDTOToZutat(detailZutatDTO);
            LOG.trace(zutat);
            manageMocktails.addIngredient(mocktailid, zutat);
            return Response.ok().build();
        }
        LOG.warn("Mocktail with ID: '" + mocktailid + "' not found");
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{mocktailid}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - mocktail deleted")
    public Response deleteMocktail(@PathParam("mocktailid") long mocktailid) {
        LOG.info("Delete mocktail with id '" + mocktailid + "'");
        if (manageMocktails.selectMocktail(mocktailid) != null) {
            manageMocktails.applyDeletion(mocktailid);
            LOG.trace("Mocktail successfully deleted");
            return Response.ok().build();
        }
        LOG.warn("No mocktail with id '" + mocktailid + "' found");
        return Response.noContent().build();
    }

    private List<ListMocktailDTO> mapMocktailToListMocktailDTO(List<Mocktail> mocktailList) {
        LOG.trace("Mapping Mocktail-List to DTO");
        List<ListMocktailDTO> mocktailDTOList = new ArrayList<>();
        for (Mocktail mocktail : mocktailList) {
            ListMocktailDTO listMocktailDTO = new ListMocktailDTO();
            listMocktailDTO.beschreibung = mocktail.getBeschreibung();
            listMocktailDTO.mocktailid = mocktail.getId();
            listMocktailDTO.name = mocktail.getName();
            mocktailDTOList.add(listMocktailDTO);
        }
        return mocktailDTOList;
    }

    private Zutat mapDetailZutatDTOToZutat(DetailZutatDTO detailZutatDTO) {
        LOG.trace("Mapping ZutatDTO to Zutat");
        String name = detailZutatDTO.name;
        float menge = detailZutatDTO.menge;
        Einheit einheit = detailZutatDTO.einheit;
        Zutat zutat = new Zutat(name, menge, einheit);
        return zutat;
    }
}
