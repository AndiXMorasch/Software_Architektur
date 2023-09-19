package de.hsos.swa.mocktail.verwalten.gateway;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonArray;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

public class CocktailWrapperDeserializer implements JsonbDeserializer<CocktailWrapperDTO> {

    @Override
    public CocktailWrapperDTO deserialize(JsonParser parser, DeserializationContext context, Type type) {
        CocktailWrapperDTO wrapper = new CocktailWrapperDTO();

        JsonArray drinks = parser.getObject().getJsonArray("drinks");
        List<CocktailDTO> cocktails = drinks.stream()
                .map((value) -> JsonbBuilder.create()
                        .fromJson(value.asJsonObject().toString(), CocktailDTO.class))
                .collect(Collectors.toList());
        wrapper.setDrinks(cocktails);
        return wrapper;
    }

}