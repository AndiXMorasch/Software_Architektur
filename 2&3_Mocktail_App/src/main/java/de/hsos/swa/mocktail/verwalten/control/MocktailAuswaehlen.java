package de.hsos.swa.mocktail.verwalten.control;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.MocktailKatalog;

@ApplicationScoped
public class MocktailAuswaehlen {
    @Inject
    MocktailKatalog mocktailKatalog;

    public List<Mocktail> selectAll() {
        return mocktailKatalog.selectAll();
    }

    public Mocktail selectMocktail(long id) {
        return mocktailKatalog.selectMocktail(id);
    }
}
