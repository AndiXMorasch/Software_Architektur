package de.hsos.pizza4me.pizzaverwaltung.gateway;

import java.util.Collection;
import java.util.List;

import de.hsos.pizza4me.pizzaverwaltung.entity.Ingredient;
import de.hsos.pizza4me.pizzaverwaltung.entity.Pizza;
import de.hsos.pizza4me.pizzaverwaltung.entity.PizzaCatalog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
@Transactional(TxType.MANDATORY)
public class PizzaRepository implements PizzaCatalog {

    @Inject
    private EntityManager em;

    @Override
    public Collection<Pizza> selectAllPizzas() {
        TypedQuery<PizzaEntity> findAll = em.createQuery("SELECT p FROM PizzaEntity p", PizzaEntity.class);
        List<PizzaEntity> entities = findAll.getResultList();
        return entities.stream()
                .map(PizzaEntity::getPizzaFromEntity)
                .toList();
    }

    @Override
    public Pizza selectPizza(Long pizzaId) {
        if (pizzaId == null) {
            throw new IllegalArgumentException("Pizza-ID cannot be null.");
        }

        PizzaEntity entity = em.find(PizzaEntity.class, pizzaId);
        if (entity == null) {
            throw new NotFoundException("Pizza with ID: '" + pizzaId + "' does not exist.");
        }
        return PizzaEntity.getPizzaFromEntity(entity);
    }

    @Override
    public Long createPizza(Pizza pizza) {
        if (!requestIsCorrect(pizza)) {
            throw new IllegalArgumentException("Some of the pizzas data is missing.");
        }

        PizzaEntity pizzaEntity = new PizzaEntity();
        pizzaEntity.setName(pizza.getName());
        pizzaEntity.setPrice(pizza.getPrice());

        this.em.persist(pizzaEntity);
        return pizzaEntity.getId();
    }

    @Override
    public Pizza modifyPizza(Pizza pizza) {
        if (!requestIsCorrect(pizza)) {
            throw new IllegalArgumentException("Some of the pizzas data is missing.");
        }
        if (pizza.getId() == null) {
            throw new IllegalArgumentException("Id of Pizza cannot be null.");
        }

        PizzaEntity entity = em.find(PizzaEntity.class, pizza.getId());
        if (entity == null) {
            throw new NotFoundException("Pizza with ID: '" + pizza.getId() + "' does not exist.");
        }

        entity.setName(pizza.getName());
        entity.setPrice(pizza.getPrice());

        this.em.merge(entity);
        return pizza;
    }

    @Override
    public boolean addIngredient(Long pizzaId, Ingredient ingredient) {
        if (!requestIsCorrect(ingredient)) {
            throw new IllegalArgumentException("Some of the ingredient data is missing.");
        }

        PizzaEntity entity = em.find(PizzaEntity.class, pizzaId);
        if (entity == null) {
            throw new NotFoundException("Pizza with ID: '" + pizzaId + "' does not exist.");
        }

        int oldSize = entity.getIngredients().size();

        IngredientEntity ingredientEntity = IngredientEntity.getEntityFromIngredient(ingredient);
        this.em.persist(ingredientEntity);
        entity.addIngredient(ingredientEntity);
        this.em.merge(entity);
        return oldSize < entity.getIngredients().size();
    }

    @Override
    public boolean deletePizza(Long pizzaId) {
        if (pizzaId == null) {
            throw new IllegalArgumentException("Pizza-ID cannot be null.");
        }

        PizzaEntity pizzaEntity = em.find(PizzaEntity.class, pizzaId);
        em.remove(pizzaEntity);
        return pizzaEntity != null;
    }

    private boolean requestIsCorrect(Pizza pizza) {
        if (pizza == null) {
            return false;
        }

        if (pizza.getName().isEmpty() || pizza.getPrice() == null) {
            return false;
        }

        return true;
    }

    private boolean requestIsCorrect(Ingredient ingredient) {
        if (ingredient == null) {
            return false;
        }

        if (ingredient.getName().isEmpty()) {
            return false;
        }

        return true;
    }

}
