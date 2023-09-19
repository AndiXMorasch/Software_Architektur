package de.hsos.swa.mocktail.verwalten.boundary.rest;

import java.util.List;

public class DetailMocktailDTO {
    public long mocktailid;
    public String name;
    public String beschreibung;
    public List<DetailZutatDTO> zutaten;
}
