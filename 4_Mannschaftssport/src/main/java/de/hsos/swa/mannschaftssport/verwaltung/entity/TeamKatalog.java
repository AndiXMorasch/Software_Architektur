package de.hsos.swa.mannschaftssport.verwaltung.entity;

import java.util.Collection;
import java.util.List;

public interface TeamKatalog {
    Collection<Team> selectAllTeams();

    Collection<Team> selectAllTeams(List<String> categories);

    Collection<Team> selectAllTeams(String name);

    Collection<Team> selectAllTeams(String name, List<String> categories);

    Team selectTeam(Long teamId);

    Team createTeam(Team team);

    Team modifyTeam(Team team);

    boolean deleteTeam(Long teamId);

    Collection<Player> listPlayersOfTeam(Long teamId);

    boolean addPlayersToTeam(Long teamId, List<Player> players);

    boolean modifyPlayersFromTeam(Long teamId, List<Player> players);

    boolean deletePlayersFromTeam(Long teamId, List<Player> players);

    Manager getManagerOfTeam(Long teamId);

    boolean setManagerOfTeam(Long teamId, Manager manager);

    boolean deleteManagerOfTeam(Long teamId);

}
