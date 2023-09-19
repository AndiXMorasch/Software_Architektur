package de.hsos.swa.mannschaftssport.verwaltung.boundary.deserialize;

import java.lang.reflect.Type;

import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.EntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.FullEntityDTO;
import de.hsos.swa.mannschaftssport.verwaltung.boundary.dto.TeamAttributeDTO;

public class EntityDeserializer implements JsonbDeserializer<EntityDTO> {

    @Override
    public EntityDTO deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        JsonObject object = parser.getObject();
        JsonObject attributes = object.getJsonObject("attributes");
        if (attributes == null) {
            EntityDTO dto = new EntityDTO();
            this.fillEntityDTO(dto, object);
            return dto;
        }
        FullEntityDTO dto = new FullEntityDTO();
        this.fillEntityDTO(dto, object);
        switch (dto.getType()) {
            case "teams" -> dto.setAttributes(JsonbBuilder.create()
                    .fromJson(attributes.toString(),
                            TeamAttributeDTO.class));
            default -> throw new JsonbException("Unknown type " + dto.getType());
        }
        return dto;
    }

    private void fillEntityDTO(EntityDTO dto, JsonObject object) {
        if (object.containsKey("id")) {
            String id = object.getString("id");
            dto.setId(id);
        }
        String type = object.getString("type");
        dto.setType(type);
    }

}
