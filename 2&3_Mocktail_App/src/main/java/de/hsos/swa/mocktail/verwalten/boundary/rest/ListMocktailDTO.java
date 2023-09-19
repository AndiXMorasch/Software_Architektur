package de.hsos.swa.mocktail.verwalten.boundary.rest;

public class ListMocktailDTO {
    public long mocktailid;
    public String name;
    public String beschreibung;

    @Override
    public String toString() {
        return "ListMocktailDTO [mocktailid=" + mocktailid + ", name=" + name + ", beschreibung=" + beschreibung + "]";
    }

}
