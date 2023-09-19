package de.hsos.pizza4me.pizzaverwaltung.entity;

import java.util.Collection;

public interface PizzaCatalog {
    Collection<Pizza> selectAllPizzas();

    Pizza selectPizza(Long pizzaId);

    Long createPizza(Pizza pizza);

    Pizza modifyPizza(Pizza pizza);

    boolean addIngredient(Long pizzaId, Ingredient ingredient);

    boolean deletePizza(Long pizzaId);
}
