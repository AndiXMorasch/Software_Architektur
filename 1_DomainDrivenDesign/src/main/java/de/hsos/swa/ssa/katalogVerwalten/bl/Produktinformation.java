package de.hsos.swa.ssa.katalogVerwalten.bl;

public class Produktinformation {
    private String beschreibung;

    public Produktinformation(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public String toString() {
        return "Beschreibung: " + beschreibung + "\n";
    }
}
