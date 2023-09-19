package de.hsos.swa.mocktail.verwalten.gateway;

import java.util.List;

import javax.json.bind.annotation.JsonbTypeDeserializer;

@JsonbTypeDeserializer(value = CocktailWrapperDeserializer.class)
public class CocktailWrapperDTO {
    private List<CocktailDTO> drinks;

    public List<CocktailDTO> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<CocktailDTO> drinks) {
        this.drinks = drinks;
    }

}
