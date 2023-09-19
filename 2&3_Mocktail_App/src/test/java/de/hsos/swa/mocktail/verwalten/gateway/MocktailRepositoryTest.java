package de.hsos.swa.mocktail.verwalten.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.swa.mocktail.verwalten.entity.Einheit;
import de.hsos.swa.mocktail.verwalten.entity.Mocktail;
import de.hsos.swa.mocktail.verwalten.entity.Zutat;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MocktailRepositoryTest {

    @Inject
    MocktailRepository mocktailRepository;

    @Inject
    EntityManager em;

    @Test
    public void givenNull_createMocktail_throwsException() {
        assertThrows(RuntimeException.class, () -> mocktailRepository.createMocktail(null));
    }

    @Test
    public void givenMocktail_createMocktail_MocktailInDatabase() {
        Mocktail mocktail = new Mocktail();
        mocktail.setName("Caipi");

        long mocktailId = mocktailRepository.createMocktail(mocktail);

        assertNotEquals(0, mocktailId);
        MocktailEntity actual = em.find(MocktailEntity.class, mocktailId);
        assertNotNull(actual);
        assertNotNull(actual);
        assertEquals("Caipi", actual.getName());
    }

    @Test
    public void givenScript_getMocktailById_getsCorrectMocktail() {
        Mocktail actual = mocktailRepository.selectMocktail(1);
        assertNotNull(actual);
        assertEquals("Sex on the couch", actual.getName());

        actual = mocktailRepository.selectMocktail(2);
        assertNotNull(actual);
        assertEquals("Short island icetea", actual.getName());
        assertFalse(actual.getZutaten().isEmpty());
        assertEquals("Limetten", actual.getZutaten().stream().findFirst().get().getName());
    }

    @Test
    public void givenScript_getAllMocktails_getsMocktails() {
        List<Mocktail> mocktails = mocktailRepository.selectAll();
        assertNotNull(mocktails);
        assertNotEquals(0, mocktails.size());
        assertTrue(mocktails.contains(new Mocktail(1L, "Sex on the couch", "Kein Alkohol, nur Saft", null)));
        assertTrue(mocktails.contains(new Mocktail(2L, "Short island icetea", "Hauptsache viel Zucker", null)));
    }

    @Test
    @Transactional
    public void givenScript_deleteMocktail_deletesMocktail() {
        Long deleteId = 3L;

        MocktailEntity before = em.find(MocktailEntity.class, deleteId);
        assertNotNull(before);

        mocktailRepository.deleteMocktail(deleteId);

        MocktailEntity after = em.find(MocktailEntity.class, deleteId);
        assertNull(after);
    }

    @Test
    @Transactional
    public void givenScriptAndMocktail_updateMocktail_updatesMocktail() {
        Long updateId = 4L;
        Mocktail mocktail = new Mocktail(updateId, "Bloody Milly", "Mit Tomaten", null);

        MocktailEntity before = em.find(MocktailEntity.class, updateId);
        assertNotNull(before);
        assertNotEquals(mocktail.getName(), before.getName());
        assertNotEquals(mocktail.getBeschreibung(), before.getBeschreibung());

        mocktailRepository.modifyMocktail(mocktail);

        MocktailEntity after = em.find(MocktailEntity.class, updateId);
        assertNotNull(after);
        assertEquals(mocktail.getName(), after.getName());
        assertEquals(mocktail.getBeschreibung(), after.getBeschreibung());
    }

    @Test
    public void givenScriptAndIngredient_addIngredient_addsIngredientToCorrectMocktail() {
        Zutat zutat = new Zutat();
        zutat.setName("Orangensaft");
        zutat.setMenge(250);
        zutat.setEinheit(Einheit.MILLILITER);

        mocktailRepository.addIngredient(1, zutat);

        MocktailEntity mocktail = em.find(MocktailEntity.class, 1L);
        assertNotNull(mocktail);
        assertNotNull(mocktail.getZutaten());
        assertFalse(mocktail.getZutaten().isEmpty());
        Optional<ZutatEntity> actual = mocktail.getZutaten().stream().findFirst();
        assertTrue(actual.isPresent());
        assertEquals("Orangensaft", actual.get().getName());
        assertEquals(250, actual.get().getMenge());
        assertEquals(Einheit.MILLILITER, actual.get().getEinheit());
    }

}
