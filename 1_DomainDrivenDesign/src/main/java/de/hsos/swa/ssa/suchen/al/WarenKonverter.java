package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.acl.WareDTO;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class WarenKonverter {

    WareDTO wareToDTO(Ware ware) {
        String beschreibung = null;
        if(ware.getProduktinformation() != null) {
            beschreibung = ware.getProduktinformation().getBeschreibung();
        } 
        return new WareDTO(ware.getWarennummer(), ware.getWarenname(), ware.getPreis(), beschreibung);
    }
}
