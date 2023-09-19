package de.hsos.swa.mannschaftssport.verwaltung.control;

import java.util.Collection;
import java.util.List;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;

public interface ManageTeams {

    Collection<Team> selectAllTeams(String name, List<String> categories);

    Team createTeam(Team team);

    Team selectTeam(Long teamId);

    Team modifyTeam(Team team);

    boolean deleteTeam(Long teamId);

    boolean addPlayersToTeam(Long teamId, List<Long> playerIds);

    Collection<Player> listPlayersFromTeam(Long teamId);

    boolean modifyPlayersFromTeam(Long teamId, List<Long> playerIds);

    boolean deletePlayerFromTeam(Long teamId, List<Long> playerIds);

    Manager getManagerOfTeam(Long teamId);

    boolean setManagerOfTeam(Long teamId, Long managerId);

    boolean deleteManagerOfTeam(Long teamId);
}
