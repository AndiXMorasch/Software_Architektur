package de.hsos.swa.mannschaftssport.verwaltung.entity;

import java.util.Collection;

public interface PlayerKatalog {
    Player selectPlayer(Long playerId);

    Collection<Player> selectAllPlayers();

    Collection<Player> selectAllPlayers(String name);

    Player createPlayer(Player player);

    Player modifyPlayer(Player player);

    boolean deletePlayer(Long playerId);
}
