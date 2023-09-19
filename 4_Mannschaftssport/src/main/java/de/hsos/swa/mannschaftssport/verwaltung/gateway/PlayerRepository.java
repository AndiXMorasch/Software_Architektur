package de.hsos.swa.mannschaftssport.verwaltung.gateway;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.PlayerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
@Transient
public class PlayerRepository implements PlayerKatalog {
    private ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        players.put(1L, new Player(1L, "Leroy Sané", "fit"));
        players.put(2L, new Player(2L, "Robert Lewandowski", "fit"));
        players.put(3L, new Player(3L, "Manuel Neuer", "verletzt"));
        players.put(4L, new Player(4L, "Marco Reus", "fit"));
    }

    @Override
    public Player selectPlayer(Long playerId) {
        if (playerId == null) {
            throw new IllegalArgumentException("playerId cannot be null");
        }

        return this.players.get(playerId);
    }

    @Override
    public Collection<Player> selectAllPlayers() {
        return this.players.values();
    }

    @Override
    public Collection<Player> selectAllPlayers(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        return this.players.values().stream()
                .filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Player createPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        player.setId((long) (Math.random() * 10000));
        this.players.put(player.getId(), player);
        return player;
    }

    @Override
    public Player modifyPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        Player p = this.players.get(player.getId());
        if(p == null) {
            return null;
        }

        p.setName(player.getName());
        p.setCondition(player.getCondition());
        return p;
    }

    @Override
    public boolean deletePlayer(Long playerId) {
        if (playerId == null) {
            throw new IllegalArgumentException("playerId cannot be null");
        }

        Player removed = this.players.remove(playerId);
        return removed != null;
    }
}
