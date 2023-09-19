package de.hsos.pizza4me.auftragsverwaltung.boundary.api;

import jakarta.validation.constraints.NotNull;

public class CustomerIdentifierDTO {
    
    @NotNull
    public Long customerId;
}
