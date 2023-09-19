package de.hsos.pizza4me.auftragsverwaltung.boundary.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NewOrderPositionDTO {

    @NotNull
    public Long pizzaId;

    @NotNull
    @NotBlank
    public String size;

    @NotNull
    public Long quantity;
}
