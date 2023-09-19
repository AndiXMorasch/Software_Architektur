package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.ManagerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.PlayerKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;
import de.hsos.swa.mannschaftssport.verwaltung.entity.TeamKatalog;
import de.hsos.swa.mannschaftssport.verwaltung.shared.Transient;

@ApplicationScoped
public class TeamService {

    @Inject
    @Transient
    private TeamKatalog teamKatalog;

    @Inject
    @Transient
    private ManagerKatalog managerKatalog;

    @Inject
    @Transient
    private PlayerKatalog playerKatalog;

    public Collection<Team> getAllTeams(String name, List<String> categories) {
        if ((name == null || name.trim().isEmpty()) && (categories == null || categories.isEmpty())) {
            return this.teamKatalog.selectAllTeams();
        }
        if (name == null || name.trim().isEmpty()) {
            return this.teamKatalog.selectAllTeams(categories);
        }
        if (categories == null || categories.isEmpty()) {
            return this.teamKatalog.selectAllTeams(name);
        }
        return this.teamKatalog.selectAllTeams(name, categories);
    }

    public Team createTeam(Team team) {
        return this.teamKatalog.createTeam(team);
    }

    public Team getTeamById(Long teamId) {
        return this.teamKatalog.selectTeam(teamId);
    }

    public Team modifyTeam(Team team) {
        return this.teamKatalog.modifyTeam(team);
    }

    public boolean deleteTeam(Long teamId) {
        return this.teamKatalog.deleteTeam(teamId);
    }

    public boolean setManagerOfTeam(Long teamId, Long managerId) {
        Manager manager = this.managerKatalog.selectManager(managerId);
        if (manager == null) {
            return false;
        }
        return this.teamKatalog.setManagerOfTeam(teamId, manager);
    }

    public Manager getManagerOfTeam(Long teamId) {
        return this.teamKatalog.getManagerOfTeam(teamId);
    }

    public boolean removeMangerFromTeam(Long teamId) {
        return this.teamKatalog.deleteManagerOfTeam(teamId);
    }

    public boolean addPlayersToTeam(Long teamId, List<Long> playerIds) {
        List<Player> players = playerIds.stream()
                .map(id -> this.playerKatalog.selectPlayer(id))
                .toList();
        if (players.contains(null)) {
            return false;
        }
        return this.teamKatalog.addPlayersToTeam(teamId, players);
    }

    public Collection<Player> getPlayersFromTeam(Long teamId) {
        return this.teamKatalog.listPlayersOfTeam(teamId);
    }

    public boolean setPlayersOfTeam(Long teamId, List<Long> playerIds) {
        List<Player> players = playerIds.stream()
                .map(id -> this.playerKatalog.selectPlayer(id))
                .toList();
        if (players.contains(null)) {
            return false;
        }
        return this.teamKatalog.modifyPlayersFromTeam(teamId, players);
    }

    public boolean deletePlayersFromTeam(Long teamId, List<Long> playerIds) {
        List<Player> players = playerIds.stream()
                .map(id -> this.playerKatalog.selectPlayer(id))
                .toList();
        if (players.contains(null)) {
            return false;
        }
        return this.teamKatalog.deletePlayersFromTeam(teamId, players);
    }
}
