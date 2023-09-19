package de.hsos.swa.mannschaftssport.verwaltung.entity;

import java.util.LinkedList;
import java.util.List;

public class Team {
    private Long id;
    private String name;
    private String category;

    private List<Player> players = new LinkedList<>();
    private Manager manager;

    public Team() {
    }

    public Team(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Team(Long id, String name, String category, List<Player> players, Manager manager) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.players = players;
        this.manager = manager;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}
