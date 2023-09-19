package de.hsos.swa.mocktail.verwalten.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import de.hsos.swa.mocktail.verwalten.entity.Cocktail;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CocktailRepositoryTest {

    @Inject
    CocktailRepository cocktailRepository;

    @Test
    public void searchForMargarita_GetsCocktails() {
        Set<Cocktail> cocktails = cocktailRepository.selectCocktailsByName("Margarita");

        assertNotNull(cocktails);
        assertNotEquals(0, cocktails.size());
        assertEquals(6, cocktails.size());
    }

}
