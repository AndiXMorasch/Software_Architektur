package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

public class TeamAttributeDTO extends AttributeDTO {
    private String name;
    private String category;

    public TeamAttributeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
