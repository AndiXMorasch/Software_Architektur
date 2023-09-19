package de.hsos.pizza4me.pizzaverwaltung.gateway;

import java.util.ArrayList;
import java.util.Collection;

import de.hsos.pizza4me.pizzaverwaltung.entity.Ingredient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Version;
import jakarta.persistence.Id;

@Entity
public class IngredientEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;
    
    private String name;

    public static Collection<Ingredient> getIngredientsFromEntities(Collection<IngredientEntity> ingredientEntities) {
        if(ingredientEntities == null) {
            return null;
        }

        Collection<Ingredient> ingredients = new ArrayList<>();
        for(IngredientEntity i : ingredientEntities) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(i.getId());
            ingredient.setName(i.getName());
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public static Collection<IngredientEntity> getEntitiesFromIngredients(Collection<Ingredient> ingredients) {
        Collection<IngredientEntity> ingredientEntities = new ArrayList<>();
        for(Ingredient i : ingredients) {
            IngredientEntity ingredient = new IngredientEntity();
            ingredient.id = i.getId();
            ingredient.name = i.getName();
            ingredientEntities.add(ingredient);
        }
        return ingredientEntities;
    }

    public static IngredientEntity getEntityFromIngredient(Ingredient ingredient) {
        IngredientEntity entity = new IngredientEntity();
        entity.name = ingredient.getName();
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
