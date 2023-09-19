package de.hsos.swa.mannschaftssport.verwaltung.boundary.deserialize;

import java.lang.reflect.Type;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.DataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.ListDataDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.SingleDataDTO;

public class DataDeserializer implements JsonbDeserializer<DataDTO> {

    @Override
    public DataDTO deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        JsonObject object = parser.getObject();
        JsonValue data = object.get("data");
        ValueType type = data.getValueType();
        if (type.equals(ValueType.ARRAY)) {
            return JsonbBuilder.create().fromJson(object.toString(), ListDataDTO.class);
        } else {
            return JsonbBuilder.create().fromJson(object.toString(), SingleDataDTO.class);
        }
    }
}
