package de.hsos.pizza4me.pizzaverwaltung.gateway;

import java.util.Collection;

import de.hsos.pizza4me.pizzaverwaltung.entity.Pizza;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;

@Entity
public class PizzaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private String name;
    private Double price;

    @ManyToMany
    private Collection<IngredientEntity> ingredients;

    public static Pizza getPizzaFromEntity(PizzaEntity entity) {
        return new Pizza(entity.getId(), entity.getName(), entity.getPrice(), IngredientEntity.getIngredientsFromEntities(entity.ingredients));
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(IngredientEntity ingredient) {
        this.ingredients.add(ingredient);
    }
}
