package de.hsos.swa.mocktail.verwalten.control;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.MocktailKatalog;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

@ApplicationScoped
public class MocktailBearbeiten {
    @Inject
    MocktailKatalog mocktailKatalog;

    public void applyModification(Mocktail mocktail) {
        mocktailKatalog.modifyMocktail(mocktail);
    }

    public void addIngredient(long mocktailId, Zutat zutat) {
        mocktailKatalog.addIngredient(mocktailId, zutat);
    }
}
