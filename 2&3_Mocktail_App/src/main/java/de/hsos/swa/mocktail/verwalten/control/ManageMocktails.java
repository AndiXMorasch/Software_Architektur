package de.hsos.swa.mocktail.verwalten.control;

import java.util.List;

import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

public interface ManageMocktails {
    public List<Mocktail> selectAllMocktails();

    public Long createMocktail(Mocktail mocktail);

    public Mocktail selectMocktail(long id);

    public void applyModification(Mocktail mocktail);

    public void applyDeletion(long id);

    public void addIngredient(long id, Zutat zutat);
}
