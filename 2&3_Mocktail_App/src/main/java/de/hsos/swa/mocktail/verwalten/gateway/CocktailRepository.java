package de.hsos.swa.mocktail.verwalten.gateway;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.hsos.swa.mocktail.verwalten.entity.Cocktail;
import de.hsos.swa.mocktail.verwalten.entity.CocktailKatalog;
import de.hsos.swa.mocktail.verwalten.entity.CocktailZutat;

@ApplicationScoped
public class CocktailRepository implements CocktailKatalog {

    @Inject
    @RestClient
    CocktailRestClient cocktailRestClient;

    @Override
    public Set<Cocktail> selectCocktailsByName(String name) {
        CocktailWrapperDTO cocktailWrapper = cocktailRestClient.getCocktailByName(name);
        return cocktailWrapper.getDrinks().stream()
                .map(this::mapCocktailDTOToCocktail)
                .collect(Collectors.toSet());

    }

    @Override
    public Set<Cocktail> selectCocktailsByNameAndIngredient(String name, String zutat) {
        CocktailWrapperDTO cocktailWrapper = cocktailRestClient.getCocktailByName(name);
        return cocktailWrapper.getDrinks().stream()
                .map(this::mapCocktailDTOToCocktail)
                .filter(cocktail -> cocktail.getZutaten().stream().anyMatch(z -> z.getName().contains(zutat)))
                .collect(Collectors.toSet());
    }

    private Cocktail mapCocktailDTOToCocktail(CocktailDTO dto) {
        Cocktail cocktail = new Cocktail();
        cocktail.setId(dto.getId());
        cocktail.setName(dto.getName());
        cocktail.setBeschreibung(dto.getBeschreibung());
        cocktail.setZutaten(dto.getZutaten().stream()
                .map(this::matZutatenDTOToCocktailZutat)
                .collect(Collectors.toList()));
        return cocktail;
    }

    private CocktailZutat matZutatenDTOToCocktailZutat(ZutatDTO dto) {
        CocktailZutat cocktailZutat = new CocktailZutat();
        cocktailZutat.setName(dto.getName());
        cocktailZutat.setMenge(dto.getMenge());
        return cocktailZutat;
    }

}
