package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;

public interface ManagePlayers {
    Player selectPlayer(Long playerId);

    Collection<Player> selectAllPlayers(String name);

    Player createPlayer(Player player);

    Player modifyPlayer(Player player);

    boolean deletePlayer(Long playerId);
}
