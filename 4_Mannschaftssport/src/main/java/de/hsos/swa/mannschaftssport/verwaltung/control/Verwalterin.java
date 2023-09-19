package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;

@ApplicationScoped
public class Verwalterin implements ManageTeams, ManageManagers, ManagePlayers {
    private TeamService teamService;
    private PlayerService playerService;
    private ManagerService managerService;

    @Inject
    public Verwalterin(TeamService teamService, PlayerService playerService, ManagerService managerService) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.managerService = managerService;
    }

    @Override
    public Collection<Team> selectAllTeams(String name, List<String> categories) {
        return this.teamService.getAllTeams(name, categories);
    }

    @Override
    public Player selectPlayer(Long playerId) {
        return this.playerService.selectPlayer(playerId);
    }

    @Override
    public Collection<Player> selectAllPlayers(String name) {
        return this.playerService.selectAllPlayers(name);
    }

    @Override
    public Player createPlayer(Player player) {
        return this.playerService.createPlayer(player);
    }

    @Override
    public Player modifyPlayer(Player player) {
        return this.playerService.modifyPlayer(player);
    }

    @Override
    public boolean deletePlayer(Long playerId) {
        return this.playerService.deletePlayer(playerId);
    }

    @Override
    public Manager selectManager(Long managerId) {
        return this.managerService.getManagerById(managerId);
    }

    @Override
    public Collection<Manager> selectAllManagers(String name) {
        return this.managerService.getAllManagers(name);
    }

    @Override
    public Manager createManager(Manager manager) {
        return this.managerService.createManager(manager);
    }

    @Override
    public Manager modifyManager(Manager manager) {
        return this.managerService.modifyManager(manager);
    }

    @Override
    public boolean deleteManager(Long managerId) {
        return this.managerService.deleteManager(managerId);
    }

    @Override
    public Team createTeam(Team team) {
        return this.teamService.createTeam(team);
    }

    @Override
    public Team selectTeam(Long teamId) {
        return this.teamService.getTeamById(teamId);
    }

    @Override
    public Team modifyTeam(Team team) {
        return this.teamService.modifyTeam(team);
    }

    @Override
    public boolean deleteTeam(Long teamId) {
        return this.teamService.deleteTeam(teamId);
    }

    @Override
    public boolean addPlayersToTeam(Long teamId, List<Long> playerIds) {
        return this.teamService.addPlayersToTeam(teamId, playerIds);
    }

    @Override
    public Collection<Player> listPlayersFromTeam(Long teamId) {
        return this.teamService.getPlayersFromTeam(teamId);
    }

    @Override
    public boolean modifyPlayersFromTeam(Long teamId, List<Long> playerIds) {
        return this.teamService.setPlayersOfTeam(teamId, playerIds);
    }

    @Override
    public boolean deletePlayerFromTeam(Long teamId, List<Long> playerIds) {
        return this.teamService.deletePlayersFromTeam(teamId, playerIds);
    }

    @Override
    public Manager getManagerOfTeam(Long teamId) {
        return this.teamService.getManagerOfTeam(teamId);
    }

    @Override
    public boolean setManagerOfTeam(Long teamId, Long managerId) {
        return this.teamService.setManagerOfTeam(teamId, managerId);
    }

    @Override
    public boolean deleteManagerOfTeam(Long teamId) {
        return this.teamService.removeMangerFromTeam(teamId);
    }

}
