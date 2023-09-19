package de.hsos.pizza4me.auftragsverwaltung.boundary.web;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

import de.hsos.pizza4me.auftragsverwaltung.control.ManageOrdersWeb;
import de.hsos.pizza4me.auftragsverwaltung.entity.Order;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@RequestScoped
@Path("/order")
@Produces(MediaType.TEXT_HTML)
@Transactional(TxType.REQUIRES_NEW)
public class OrderWebResource {

    @Inject
    ManageOrdersWeb orderManager;

    @Context
    SecurityContext context;

    private final Template page;

    public OrderWebResource(Template order) {
        this.page = requireNonNull(order, "page is required");
    }

    @GET
    public TemplateInstance order() {
        String username = this.context.getUserPrincipal().getName();
        Order order = this.orderManager.getOrderForCustomer(username);
        Collection<PizzaDTO> pizzas = this.orderManager.selectAllPizzas();
        return page.data("order", order,
                "pizzas", pizzas);
    }
}
