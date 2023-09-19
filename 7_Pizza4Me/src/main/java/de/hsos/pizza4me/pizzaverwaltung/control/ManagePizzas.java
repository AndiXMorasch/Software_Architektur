package de.hsos.pizza4me.pizzaverwaltung.control;

import java.util.Collection;

import de.hsos.pizza4me.pizzaverwaltung.entity.Ingredient;
import de.hsos.pizza4me.pizzaverwaltung.entity.Pizza;

public interface ManagePizzas {
    Collection<Pizza> selectAllPizzas();

    Pizza selectPizza(Long pizzaId);

    Long createPizza(Pizza pizza);

    Pizza modifyPizza(Pizza pizza);

    boolean addIngredient(Long pizzaId, Ingredient ingredient);

    boolean deletePizza(Long pizzaId);
}
