package de.hsos.swa.mocktail.verwalten.boundary.rest;

import javax.ws.rs.Produces;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import de.hsos.swa.mocktail.verwalten.control.ManageCocktails;
import de.hsos.swa.mocktail.verwalten.entity.Cocktail;

@Path("/cocktails")
public class CocktailRessource {

    private static final Logger LOG = Logger.getLogger(MocktailRessource.class);

    @Inject
    ManageCocktails manageDrinks;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cocktailname}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackGetCocktailsByName")
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - cocktail returned")
    public Response getCocktail(@PathParam("cocktailname") String cocktailname) {
        LOG.info("Get cocktail by name '" + cocktailname + "'");
        Set<Cocktail> cocktailSet = manageDrinks.selectCocktailByName(cocktailname);
        LOG.trace(cocktailSet);
        return Response.ok(cocktailSet).build();
    }

    public Response fallbackGetCocktailsByName(@PathParam("cocktailname") String cocktailname) {
        LOG.warn("Fallback Method called - Getting cocktail by name 'margarita'");
        Set<Cocktail> cocktailSet = manageDrinks.selectCocktailByName("margarita");
        return Response.ok(cocktailSet).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cocktailname}/{zutat}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackGetCocktailsByName")
    @CircuitBreaker(requestVolumeThreshold = 4)
    @APIResponse(responseCode = "200", description = "OK - cocktail returned")
    public Response getCocktail(@PathParam("cocktailname") String cocktailname, @PathParam("zutat") String zutat) {
        LOG.info("Get cocktail by name '" + cocktailname + "' and ingredient '" + zutat + "'");
        Set<Cocktail> cocktailSet = manageDrinks.selectCocktailByNameAndIngredient(cocktailname, zutat);
        LOG.trace(cocktailSet);
        return Response.ok(cocktailSet).build();
    }

    public Response fallbackGetCocktailsByName(@PathParam("cocktailname") String cocktailname,
            @PathParam("zutat") String zutat) {
        LOG.warn("Fallback Method called - Getting cocktail by name 'margarita'");
        Set<Cocktail> cocktailSet = manageDrinks.selectCocktailByName("margarita");
        return Response.ok(cocktailSet).build();
    }
}
