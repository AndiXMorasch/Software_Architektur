package de.hsos.pizza4me.auftragsverwaltung.acl;

import java.util.Collection;

import de.hsos.pizza4me.kundenverwaltung.acl.CustomerInformationProvider;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaDTO;
import de.hsos.pizza4me.pizzaverwaltung.acl.PizzaInformationProvider;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class OrderInformationObtainer {
    
    @Inject
    PizzaInformationProvider pizzaInfo;

    @Inject
    CustomerInformationProvider customerInfo;

    public PizzaDTO obtainPizzaInfo(Long pizzaId) {
        return pizzaInfo.providePizzaInfo(pizzaId);
    }

    public Collection<PizzaDTO> obtainAllPizzas() {
        return pizzaInfo.provideListOfPizzas();
    }
}
