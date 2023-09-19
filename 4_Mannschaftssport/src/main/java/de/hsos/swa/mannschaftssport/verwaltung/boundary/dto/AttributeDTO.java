package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import de.hsos.swa.mannschaftssport.verwaltung.entity.Manager;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Player;
import de.hsos.swa.mannschaftssport.verwaltung.entity.Team;

public abstract class AttributeDTO {
    public static AttributeDTO getAttributesOfTeam(Team team) {
        TeamAttributeDTO attribues = new TeamAttributeDTO();
        attribues.setName(team.getName());
        attribues.setCategory(team.getCategory());
        return attribues;
    }

    public static AttributeDTO getAttributesOfManager(Manager manager) {
        ManagerAttributeDTO attributes = new ManagerAttributeDTO();
        attributes.setName(manager.getName());
        return attributes;
    }

    public static AttributeDTO getAttributesOfPlayer(Player player) {
        PlayerAttributeDTO attributes = new PlayerAttributeDTO();
        attributes.setName(player.getName());
        attributes.setCondition(player.getCondition());
        return attributes;
    }
}
