package de.hsos.swa.mocktail.verwalten.entity;

import java.util.List;

public class Cocktail {
    private Long id;
    private String name;
    private String beschreibung;
    private List<CocktailZutat> zutaten;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public List<CocktailZutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<CocktailZutat> zutaten) {
        this.zutaten = zutaten;
    }

}
