package de.hsos.swa.mocktail.verwalten.gateway;

import java.util.LinkedList;
import java.util.List;

import javax.json.bind.annotation.JsonbTypeDeserializer;

@JsonbTypeDeserializer(value = CocktailDeserializer.class)
public class CocktailDTO {
    private Long id;
    private String name;
    private String beschreibung;
    private List<ZutatDTO> zutaten = new LinkedList<>();

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

    public List<ZutatDTO> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<ZutatDTO> zutaten) {
        this.zutaten = zutaten;
    }

    public void addZutat(ZutatDTO zutat) {
        this.zutaten.add(zutat);
    }

}
