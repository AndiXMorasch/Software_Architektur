package de.hsos.swa.mocktail.verwalten.control;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.MocktailKatalog;

@ApplicationScoped
public class MocktailAnlegen {
    @Inject
    MocktailKatalog mocktailKatalog;

    public Long createMocktail(Mocktail mocktail) {
        return mocktailKatalog.createMocktail(mocktail);
    }
}
