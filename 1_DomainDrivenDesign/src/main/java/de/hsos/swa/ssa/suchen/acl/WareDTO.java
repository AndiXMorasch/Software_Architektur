package de.hsos.swa.ssa.suchen.acl;

public class WareDTO {
    public long nummer;
    public String name;
    public int preis;
    public String beschreibung;

    public WareDTO(long nummer, String name, int preis, String beschreibung) {
        this.nummer = nummer;
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }
}
