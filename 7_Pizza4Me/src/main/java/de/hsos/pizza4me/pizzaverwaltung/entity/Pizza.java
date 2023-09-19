package de.hsos.pizza4me.pizzaverwaltung.entity;
import java.util.Collection;

public class Pizza {
    private Long id;
    private String name;
    private Double price;
    private Collection<Ingredient> ingredients;

    public Pizza() {

    }

    public Pizza(Long id, String name, Double price, Collection<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
