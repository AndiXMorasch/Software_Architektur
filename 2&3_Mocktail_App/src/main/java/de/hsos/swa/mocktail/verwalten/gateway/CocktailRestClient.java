package de.hsos.swa.mocktail.verwalten.gateway;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailRestClient {
    @GET
    @Path("/search.php")
    CocktailWrapperDTO getCocktailByName(@QueryParam("s") String name);
}
