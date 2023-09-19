package de.hsos.pizza4me.kundenverwaltung.boundary.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDTO {

    public Long id;

    @NotNull @NotBlank
    public String name;

    @Valid
    @NotNull
    public AddressDTO address;
}
