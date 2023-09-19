package de.hsos.swa.mocktail.verwalten.control;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.verwalten.entity.MocktailKatalog;

@ApplicationScoped
public class MocktailLoeschen {
    @Inject
    MocktailKatalog mocktailKatalog;

    public void deleteMocktail(long id) {
        mocktailKatalog.deleteMocktail(id);
    }
}
