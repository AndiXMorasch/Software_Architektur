package de.hsos.pizza4me.pizzaverwaltung.boundary.api;

import java.util.ArrayList;
import java.util.Collection;

import de.hsos.pizza4me.pizzaverwaltung.control.ManagePizzas;
import de.hsos.pizza4me.pizzaverwaltung.entity.Ingredient;
import de.hsos.pizza4me.pizzaverwaltung.entity.Pizza;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@RequestScoped
@Transactional(TxType.REQUIRES_NEW)
@Path("/pizzas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public class PizzaResource {

    @Inject
    ManagePizzas managePizzas;

    @GET
    public Response getAllPizzas() {
        Collection<Pizza> pizzas = this.managePizzas.selectAllPizzas();
        Collection<PizzaDTO> dtos = pizzas.stream().map(this::mapPizzaToPizzaDTO).toList();
        return Response.ok()
                .entity(dtos)
                .build();
    }

    @POST
    @RolesAllowed("admin")
    public Response createPizza(@Valid NewPizzaDTO pizza) {
        Long pizzaId = this.managePizzas.createPizza(mapNewPizzaDTOToPizza(pizza));
        if (pizzaId != null) {
            return Response.ok(pizzaId).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{pizzaid}")
    public Response getPizzaDetails(@PathParam("pizzaid") Long pizzaId) {
        if (pizzaId != null) {
            Pizza pizza = this.managePizzas.selectPizza(pizzaId);
            PizzaDTO pizzaDTO = mapPizzaToPizzaDTO(pizza);
            return Response.ok(pizzaDTO).build();
        }

        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @RolesAllowed("admin")
    @Path("/{pizzaid}")
    public Response modifyPizza(@PathParam("pizzaid") Long pizzaId, @Valid PizzaDTO pizzaDTO) {
        Pizza pizza = this.managePizzas.modifyPizza(mapPizzaDTOToPizza(pizzaDTO));
        if (pizza != null) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @POST
    @RolesAllowed("admin")
    @Path("/{pizzaid}")
    public Response addIngredient(@PathParam("pizzaid") Long pizzaId, @Valid IngredientDTO ingredientDTO) {
        boolean added = this.managePizzas.addIngredient(pizzaId, mapIngredientDTOToIngredient(ingredientDTO));
        if (added) {
            return Response.ok().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{pizzaid}")
    public Response deletePizza(@PathParam("pizzaid") Long pizzaId) {
        boolean deleted = this.managePizzas.deletePizza(pizzaId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    private PizzaDTO mapPizzaToPizzaDTO(Pizza pizza) {
        PizzaDTO dto = new PizzaDTO();
        dto.id = pizza.getId();
        dto.name = pizza.getName();
        dto.price = pizza.getPrice();
        dto.ingredients = mapIngredientsToIngredientDTOs(pizza.getIngredients());
        return dto;
    }

    private Collection<IngredientDTO> mapIngredientsToIngredientDTOs(Collection<Ingredient> ingredients) {
        if (ingredients == null) {
            return null;
        }

        Collection<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for (Ingredient i : ingredients) {
            IngredientDTO dto = new IngredientDTO();
            dto.name = i.getName();
            ingredientDTOs.add(dto);
        }
        return ingredientDTOs;
    }

    private Ingredient mapIngredientDTOToIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.name);
        return ingredient;
    }

    private Pizza mapPizzaDTOToPizza(PizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setId(pizzaDTO.id);
        pizza.setName(pizzaDTO.name);
        pizza.setPrice(pizzaDTO.price);
        return pizza;
    }

    private Pizza mapNewPizzaDTOToPizza(NewPizzaDTO pizzaDTO) {
        Pizza pizza = new Pizza();
        pizza.setName(pizzaDTO.name);
        pizza.setPrice(pizzaDTO.price);
        return pizza;
    }
}
