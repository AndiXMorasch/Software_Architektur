package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import javax.json.bind.annotation.JsonbTypeDeserializer;

import de.hsos.swa.mannschaftssport.verwaltung.boundary.deserialize.EntityDeserializer;

@JsonbTypeDeserializer(EntityDeserializer.class)
public class EntityDTO {
    private String id;
    private String type;

    public EntityDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
