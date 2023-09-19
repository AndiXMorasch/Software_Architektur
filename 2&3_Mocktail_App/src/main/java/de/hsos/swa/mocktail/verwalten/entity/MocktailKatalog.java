package de.hsos.swa.mocktail.verwalten.entity;

import java.util.List;

public interface MocktailKatalog {
    Mocktail selectMocktail(long id);

    List<Mocktail> selectAll();

    Long createMocktail(Mocktail mocktail);

    void modifyMocktail(Mocktail mocktail);

    void deleteMocktail(long id);

    void addIngredient(long mocktailId, Zutat zutat);

}
