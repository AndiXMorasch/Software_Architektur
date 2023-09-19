package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

public class SelfLinkDTO extends LinkDTO {
    private String self;

    public SelfLinkDTO() {
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
