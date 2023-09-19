package de.hsos.swa.mocktail.verwalten.gateway;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.MocktailKatalog;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

@ApplicationScoped
@Named("mocktailRepository")
public class MocktailRepository implements MocktailKatalog {
    @Inject
    EntityManager em;

    @Override
    public Mocktail selectMocktail(long id) {
        MocktailEntity entity = em.find(MocktailEntity.class, id);
        return mapMocktailEntityToMocktail(entity);
    }

    @Override
    @Transactional
    public Long createMocktail(Mocktail mocktail) {
        if (mocktail == null) {
            throw new IllegalArgumentException("Mocktail cannot be null");
        }
        MocktailEntity entity = new MocktailEntity();
        entity.setName(mocktail.getName());
        entity.setBeschreibung(mocktail.getBeschreibung());
        em.persist(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void modifyMocktail(Mocktail mocktail) {
        MocktailEntity entity = em.find(MocktailEntity.class, mocktail.getId());
        entity.setName(mocktail.getName());
        entity.setBeschreibung(mocktail.getBeschreibung());
        em.persist(entity);
    }

    @Override
    @Transactional
    public void deleteMocktail(long id) {
        MocktailEntity entity = em.find(MocktailEntity.class, id);
        em.remove(entity);
    }

    @Override
    public List<Mocktail> selectAll() {
        Query query = em.createQuery("SELECT m FROM MocktailEntity m");
        List<MocktailEntity> results = query.getResultList();

        return results.stream()
                .map(this::mapMocktailEntityToMocktail)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addIngredient(long mocktailId, Zutat zutat) {
        MocktailEntity mocktail = em.find(MocktailEntity.class, mocktailId);
        ZutatEntity entity = new ZutatEntity();
        entity.setName(zutat.getName());
        entity.setMenge(zutat.getMenge());
        entity.setEinheit(zutat.getEinheit());
        entity.setMocktail(mocktail);
        em.persist(entity);
    }

    private Mocktail mapMocktailEntityToMocktail(MocktailEntity entity) {
        return new Mocktail(entity.getId(), entity.getName(), entity.getBeschreibung(),
                entity.getZutaten().stream().map(this::mapZutatEntityToZutat).collect(Collectors.toList()));
    }

    private Zutat mapZutatEntityToZutat(ZutatEntity entity) {
        return new Zutat(entity.getName(), entity.getMenge(), entity.getEinheit());
    }
}
