package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

import javax.ws.rs.core.Link;

public abstract class LinkDTO {

    public static LinkDTO getSelfLinkDTO(Link link) {
        SelfLinkDTO dto = new SelfLinkDTO();
        dto.setSelf(link.getUri().toString());
        return dto;
    }
}
