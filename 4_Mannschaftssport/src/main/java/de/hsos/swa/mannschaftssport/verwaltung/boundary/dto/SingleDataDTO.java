package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import java.util.List;

public class SingleDataDTO extends DataDTO {
    private EntityDTO data;

    private List<EntityDTO> included;

    public EntityDTO getData() {
        return data;
    }

    public void setData(EntityDTO data) {
        this.data = data;
    }

    public List<EntityDTO> getIncluded() {
        return included;
    }

    public void setIncluded(List<EntityDTO> included) {
        this.included = included;
    }

    @Override
    public List<EntityDTO> dataAsList() {
        return List.of(this.data);
    }

}
