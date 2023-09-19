package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import java.util.List;

public class ListDataDTO extends DataDTO {
    private List<EntityDTO> data;

    public List<EntityDTO> getData() {
        return data;
    }

    public void setData(List<EntityDTO> data) {
        this.data = data;
    }

    @Override
    public List<EntityDTO> dataAsList() {
        return this.data;
    }
}
