package de.hsos.swa.mannschaftssport.verwaltung.boundary.dto;

public class FullEntityDTO extends EntityDTO {

    private AttributeDTO attributes;
    private LinkDTO links;

    public FullEntityDTO() {
    }

    public AttributeDTO getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeDTO attributes) {
        this.attributes = attributes;
    }

    public LinkDTO getLinks() {
        return links;
    }

    public void setLinks(LinkDTO links) {
        this.links = links;
    }

}
