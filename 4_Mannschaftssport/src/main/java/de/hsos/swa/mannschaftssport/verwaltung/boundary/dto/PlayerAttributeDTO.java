package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

public class PlayerAttributeDTO extends AttributeDTO {
    private String name;
    private String condition;

    public PlayerAttributeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
