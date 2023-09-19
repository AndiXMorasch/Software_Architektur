package de.hsos.swa.mocktail.verwalten.entity;

import java.util.Set;

public interface CocktailKatalog {
    Set<Cocktail> selectCocktailsByName(String name);

    Set<Cocktail> selectCocktailsByNameAndIngredient(String name, String zutat);
}
