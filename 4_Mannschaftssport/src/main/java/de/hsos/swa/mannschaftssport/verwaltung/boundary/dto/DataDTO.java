package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import java.util.List;

import javax.json.bind.annotation.JsonbTypeDeserializer;

import de.hsos.swa.mannschaftssport.verwaltung.boundary.deserialize.DataDeserializer;

@JsonbTypeDeserializer(DataDeserializer.class)
public abstract class DataDTO {
    public abstract List<EntityDTO> dataAsList();
}
