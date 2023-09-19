package de.hsos.swa.mocktail.verwalten.boundary.rest;

public class CreateMocktailDTO {
    public String name;
    public String beschreibung;

    @Override
    public String toString() {
        return "CreateMocktailDTO [name=" + name + ", beschreibung=" + beschreibung + "]";
    }

}
