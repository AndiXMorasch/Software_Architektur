package de.hsos.swa.mocktail.verwalten.gateway;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.json.JsonValue;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

public class CocktailDeserializer implements JsonbDeserializer<CocktailDTO> {

    @Override
    public CocktailDTO deserialize(JsonParser parser, DeserializationContext context, Type type) {
        CocktailDTO cocktail = new CocktailDTO();

        Stream<Entry<String, JsonValue>> objectStream = parser.getObjectStream();
        Iterator<Entry<String, JsonValue>> iterator = objectStream.iterator();
        while (iterator.hasNext()) {
            Entry<String, JsonValue> entry = iterator.next();
            if (entry.getKey().equals("idDrink")) {
                String idString = entry.getValue().toString();
                idString = idString.replace("\"", "");
                cocktail.setId(Long.parseLong(idString));
            } else if (entry.getKey().equals("strDrink")) {
                cocktail.setName(entry.getValue().toString());
            } else if (entry.getKey().equals("strInstructions")) {
                cocktail.setBeschreibung(entry.getValue().toString());
            } else if (entry.getKey().startsWith("strIngredient")) {
                JsonValue value = entry.getValue();
                if (value != null && !value.toString().equals("null")) {
                    ZutatDTO zutat = new ZutatDTO();
                    zutat.setName(value.toString());
                    cocktail.addZutat(zutat);
                }
            } else if (entry.getKey().startsWith("strMeasure")) {
                JsonValue value = entry.getValue();
                if (value != null && !value.toString().equals("null")) {
                    int index = Integer.parseInt(entry.getKey().substring(10));
                    ZutatDTO zutat = cocktail.getZutaten().get(index - 1);
                    zutat.setMenge(value.toString());
                }
            }
        }

        return cocktail;
    }

}