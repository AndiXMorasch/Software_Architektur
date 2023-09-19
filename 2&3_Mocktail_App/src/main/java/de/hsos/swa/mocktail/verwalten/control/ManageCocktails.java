package de.hsos.swa.mocktail.verwalten.control;

import java.util.Set;

import de.hsos.swa.mocktail.verwalten.entity.Cocktail;

public interface ManageCocktails {
    public Set<Cocktail> selectCocktailByNameAndIngredient(String name, String zutat);

    public Set<Cocktail> selectCocktailByName(String name);
}
