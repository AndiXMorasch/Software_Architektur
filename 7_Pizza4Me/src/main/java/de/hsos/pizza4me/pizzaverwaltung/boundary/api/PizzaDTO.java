package de.hsos.pizza4me.pizzaverwaltung.boundary.api;

import java.util.Collection;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PizzaDTO {

    @NotNull
    public Long id;

    @NotNull
    @NotBlank
    public String name;

    @NotNull
    public Double price;

    @Valid
    public Collection<IngredientDTO> ingredients;
}
