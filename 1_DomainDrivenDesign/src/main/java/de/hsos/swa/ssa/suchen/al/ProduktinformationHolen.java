package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class ProduktinformationHolen {

    private Katalog katalog;

    public ProduktinformationHolen(Katalog katalog) {
        this.katalog = katalog;
    }

    Produktinformation holeProduktinformation(Ware ware) {
        return this.katalog.gebeProduktinformation(ware);
    }
}
