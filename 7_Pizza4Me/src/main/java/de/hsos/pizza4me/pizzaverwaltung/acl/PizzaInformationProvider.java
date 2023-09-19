package de.hsos.pizza4me.pizzaverwaltung.acl;

import java.util.Collection;

public interface PizzaInformationProvider {
    
    PizzaDTO providePizzaInfo(Long pizzaId);

    Collection<PizzaDTO> provideListOfPizzas();
}
