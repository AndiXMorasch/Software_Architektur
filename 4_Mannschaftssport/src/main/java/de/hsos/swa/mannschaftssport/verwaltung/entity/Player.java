package de.hsos.swa.mannschaftssport.verwaltung.entity;

public class Player {

    private Long id;
    private String name;
    private String condition;

    public Player() {
        
    }

    public Player(Long id, String name, String condition) {
        this.id = id;
        this.name = name;
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
