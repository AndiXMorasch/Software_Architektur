package de.hsos.pizza4me.pizzaverwaltung.control;

import java.util.ArrayList;
import java.util.Collection;

import de.hsos.pizza4me.pizzaverwaltung.acl.IngredientDTO;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaDTO;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaInformationProvider;
import de.hsos.pizza4me.pizzaverwaltung.entity.Ingredient;
import de.hsos.pizza4me.pizzaverwaltung.entity.Pizza;
import de.hsos.pizza4me.pizzaverwaltung.entity.PizzaCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PizzaService implements ManagePizzas, PizzaInformationProvider {

    @Inject
    PizzaCatalog pizzaCatalog;

    @Override
    public Collection<Pizza> selectAllPizzas() {
        return this.pizzaCatalog.selectAllPizzas();
    }

    @Override
    public Pizza selectPizza(Long pizzaId) {
        return this.pizzaCatalog.selectPizza(pizzaId);
    }

    @Override
    public Long createPizza(Pizza pizza) {
        return this.pizzaCatalog.createPizza(pizza);
    }

    @Override
    public Pizza modifyPizza(Pizza pizza) {
        return this.pizzaCatalog.modifyPizza(pizza);
    }

    @Override
    public boolean addIngredient(Long pizzaId, Ingredient ingredient) {
        return this.pizzaCatalog.addIngredient(pizzaId, ingredient);
    }

    @Override
    public boolean deletePizza(Long pizzaId) {
        return this.pizzaCatalog.deletePizza(pizzaId);
    }

    @Override
    public PizzaDTO providePizzaInfo(Long pizzaId) {
        Pizza pizza = this.selectPizza(pizzaId);
        if(pizza == null) {
            return null;
        }
        return mapPizzaToPizzaDTO(pizza);
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
        Collection<IngredientDTO> ingredientDTOs = new ArrayList<>();
        for(Ingredient i : ingredients) {
            IngredientDTO dto = new IngredientDTO();
            dto.name = i.getName();
            ingredientDTOs.add(dto);
        }
        return ingredientDTOs;
    }

    @Override
    public Collection<PizzaDTO> provideListOfPizzas() {
        Collection<PizzaDTO> pizzaDTOs = new ArrayList<>();
        Collection<Pizza> pizzas = this.pizzaCatalog.selectAllPizzas();
        for(Pizza p : pizzas) {
            PizzaDTO pizza = new PizzaDTO();
            pizza.id = p.getId();
            pizza.ingredients = mapIngredientsToIngredientDTOs(p.getIngredients());
            pizza.name = p.getName();
            pizza.price = p.getPrice();
            pizzaDTOs.add(pizza);
        }
        return pizzaDTOs;
    }
}
