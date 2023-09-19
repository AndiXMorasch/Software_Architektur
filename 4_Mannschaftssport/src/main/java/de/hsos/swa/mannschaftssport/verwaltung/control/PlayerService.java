package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.PlayerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;

@ApplicationScoped
public class PlayerService {
    @Inject
    @Transient
    private PlayerKatalog playerKatalog;

    public Collection<Player> selectAllPlayers(String name) {
        if(name == null) {
            return this.playerKatalog.selectAllPlayers();
        }
        return this.playerKatalog.selectAllPlayers(name);
    }

    public Player selectPlayer(Long id) {
        return this.playerKatalog.selectPlayer(id);
    }

    public Player createPlayer(Player player) {
        return this.playerKatalog.createPlayer(player);
    }

    public Player modifyPlayer(Player player) {
        return this.playerKatalog.modifyPlayer(player);
    }

    public boolean deletePlayer(Long id) {
        return this.playerKatalog.deletePlayer(id);
    }
}
