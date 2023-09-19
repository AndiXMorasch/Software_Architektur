package de.hsos.swa.mocktail.verwalten.boundary.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModifyMocktailDTO {
    public long mocktailid;
    public String name;
    public String beschreibung;
}
