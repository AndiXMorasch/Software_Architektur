package de.hsos.swa.ssa.suchen.bl;

public class Ware {
    private long warennummer;
    private String warenname;
    private int preis;
    private Produktinformation produktinformation;

    public Ware(long warennummer, String warenname, int preis) {
        this.warennummer = warennummer;
        this.warenname = warenname;
        this.preis = preis;
    }

    public int getPreis() {
        return preis;
    }

    public Produktinformation getProduktinformation() {
        return produktinformation;
    }

    public void setProduktinformation(Produktinformation produktinformation) {
        this.produktinformation = produktinformation;
    }

    public String getWarenname() {
        return warenname;
    }

    public long getWarennummer() {
        return warennummer;
    }

    @Override
    public String toString() {
        String s = "Warennummer: " + warennummer + "\n" + "Warenname: " + warenname + "\n" + "Preis: " + preis + " Euro" + "\n";
        if(produktinformation != null) {
            s += getProduktinformation().toString();
        }
        return s;
    }
}
