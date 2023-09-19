package de.hsos.swa.mocktail.verwalten.control;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.verwalten.entity.Cocktail;
import de.hsos.swa.mocktail.verwalten.entity.CocktailKatalog;

@ApplicationScoped
public class CocktailAuswaehlen {
    @Inject
    CocktailKatalog cocktailKatalog;

    public Set<Cocktail> selectCocktailsByNameAndIngredient(String name, String zutat) {
        return cocktailKatalog.selectCocktailsByNameAndIngredient(name, zutat);
    }

    public Set<Cocktail> selectCocktailByName(String name) {
        return cocktailKatalog.selectCocktailsByName(name);
    }
}
