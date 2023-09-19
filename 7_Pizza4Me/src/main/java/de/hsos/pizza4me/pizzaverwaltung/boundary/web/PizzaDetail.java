package de.hsos.pizza4me.pizzaverwaltung.boundary.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional.TxType;

import static java.util.Objects.requireNonNull;

import de.hsos.pizza4me.pizzaverwaltung.control.ManagePizzas;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@RequestScoped
@Path("/pizza-details")
@Produces(MediaType.TEXT_HTML)
@Transactional(TxType.REQUIRES_NEW)
public class PizzaDetail {

    @Inject
    ManagePizzas pizzaManager;

    private final Template page;

    public PizzaDetail(Template pizza) {
        this.page = requireNonNull(pizza, "page is required");
    }
    
    @GET
    public TemplateInstance getPizza(@QueryParam("id") Long pizzaId) {
        return page.data("pizza", pizzaManager.selectPizza(pizzaId));
    }
}
