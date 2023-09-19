package de.hsos.pizza4me.pizzaverwaltung.boundary.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IngredientDTO {
    @NotNull @NotBlank
    public String name;
}
