package de.hsos.pizza4me.kundenverwaltung.boundary.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

    @NotNull @NotBlank
    public String cityCode;

    @NotNull @NotBlank
    public String city;

    @NotNull @NotBlank
    public String street;

    @NotNull @NotBlank
    public String houseNumber;
}
