package de.hsos.swa.mocktail.verwalten.control;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsos.swa.mocktail.verwalten.entity.Cocktail;
import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

@ApplicationScoped
@Named("verwalterIn")
public class VerwalterIn implements ManageMocktails, ManageCocktails {

    @Inject
    MocktailAnlegen mocktailAnlegen;
    @Inject
    MocktailAuswaehlen mocktailAuswaehlen;
    @Inject
    MocktailBearbeiten mocktailBearbeiten;
    @Inject
    MocktailLoeschen mocktailLoeschen;
    @Inject
    CocktailAuswaehlen cocktailAuswaehlen;

    @Override
    public List<Mocktail> selectAllMocktails() {
        return mocktailAuswaehlen.selectAll();
    }

    @Override
    public Long createMocktail(Mocktail mocktail) {
        return mocktailAnlegen.createMocktail(mocktail);
    }

    @Override
    public Mocktail selectMocktail(long id) {
        return mocktailAuswaehlen.selectMocktail(id);
    }

    @Override
    public void applyModification(Mocktail mocktail) {
        mocktailBearbeiten.applyModification(mocktail);
    }

    @Override
    public void applyDeletion(long id) {
        mocktailLoeschen.deleteMocktail(id);
    }

    @Override
    public void addIngredient(long mocktailId, Zutat zutat) {
        mocktailBearbeiten.addIngredient(mocktailId, zutat);
    }

    @Override
    public Set<Cocktail> selectCocktailByName(String name) {
        return this.cocktailAuswaehlen.selectCocktailByName(name);
    }

    @Override
    public Set<Cocktail> selectCocktailByNameAndIngredient(String name, String zutat) {
        return this.cocktailAuswaehlen.selectCocktailsByNameAndIngredient(name, zutat);
    }

}
