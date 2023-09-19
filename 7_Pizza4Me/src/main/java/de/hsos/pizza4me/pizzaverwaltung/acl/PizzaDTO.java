package de.hsos.pizza4me.pizzaverwaltung.acl;

import java.util.Collection;

public class PizzaDTO {
    public Long id;
    public String name;
    public Double price;
    public Collection<IngredientDTO> ingredients;
}
