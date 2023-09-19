package de.hsos.swa.mannschaftssport.verwaltung.gateway;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;
import de.hsos.swa.mannschaftssport.verwaltung.entity.TeamKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;

@ApplicationScoped
@Transient
public class TeamRepository implements TeamKatalog {
    private ConcurrentHashMap<Long, Team> teams = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        teams.put(1L, new Team(1L, "Bayern München", "seniors"));
        teams.put(2L, new Team(2L, "Bayern München", "juniors"));
        teams.put(3L, new Team(3L, "BVB", "seniors"));
        teams.put(4L, new Team(4L, "BVB", "juniors"));
    }

    @Override
    public Collection<Team> selectAllTeams() {
        return this.teams.values();
    }

    @Override
    public Collection<Team> selectAllTeams(List<String> categories) {
        if (categories == null) {
            throw new IllegalArgumentException("categories cannot be null");
        }

        return this.teams.values().stream()
                .filter(t -> categories.contains(t.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Team> selectAllTeams(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }

        return this.teams.values().stream()
                .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Team> selectAllTeams(String name, List<String> categories) {
        if (name == null || categories == null || categories.isEmpty()) {
            throw new IllegalArgumentException("name and category cannot be null or empty");
        }

        return this.teams.values().stream()
                .filter(t -> t.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(t -> categories.contains(t.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public Team selectTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }

        return this.teams.get(teamId);
    }

    @Override
    public Team createTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team cannot be null");
        }
        team.setId((long) (Math.random() * 10000));
        this.teams.put(team.getId(), team);
        return team;
    }

    @Override
    public Team modifyTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("team cannot be null");
        }

        Team t = this.teams.get(team.getId());
        if (t == null) {
            return null;
        }
        t.setCategory(team.getCategory());
        t.setName(team.getName());
        return t;
    }

    @Override
    public boolean deleteTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }

        Team removed = this.teams.remove(teamId);
        return removed != null;
    }

    @Override
    public Collection<Player> listPlayersOfTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return null;
        }
        return team.getPlayers();
    }

    @Override
    public boolean addPlayersToTeam(Long teamId, List<Player> players) {
        Team team = this.teams.get(teamId);
        if (team == null) {
            return false;
        }
        return team.getPlayers().addAll(players);
    }

    @Override
    public boolean modifyPlayersFromTeam(Long teamId, List<Player> players) {
        if (teamId == null || players == null || players.isEmpty()) {
            throw new IllegalArgumentException("teamId and players cannot be null or empty");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return false;
        }

        team.getPlayers().clear();
        return team.getPlayers().addAll(players);
    }

    @Override
    public boolean deletePlayersFromTeam(Long teamId, List<Player> players) {
        if (teamId == null || players == null || players.isEmpty()) {
            throw new IllegalArgumentException("teamId and players cannot be null or empty");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return false;
        }

        return team.getPlayers().removeAll(players);
    }

    @Override
    public Manager getManagerOfTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return null;
        }
        return team.getManager();
    }

    @Override
    public boolean setManagerOfTeam(Long teamId, Manager manager) {
        if (teamId == null || manager == null) {
            throw new IllegalArgumentException("teamId and manager cannot be null");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return false;
        }
        team.setManager(manager);
        return true;
    }

    @Override
    public boolean deleteManagerOfTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("teamId cannot be null");
        }

        Team team = this.teams.get(teamId);
        if (team == null) {
            return false;
        }
        team.setManager(null);
        return true;
    }

}
