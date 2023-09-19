package de.hsos.swa.ssa.suchen.al;

import de.hsos.swa.ssa.suchen.acl.WareDTO;
import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class WarenkorbVerwalten {
    private WarenKonverter warenKonverter;
    private WarenkorbFuerSuche warenkorbFuerSuche;

    public WarenkorbVerwalten(WarenkorbFuerSuche warenkorbFuerSuche) {
        this.warenKonverter = new WarenKonverter();
        this.warenkorbFuerSuche = warenkorbFuerSuche;
    }

    void wareHinzufuegen(WareDTO ware) {
        warenkorbFuerSuche.wareHinzufuegen(ware);
    }

    WareDTO konvertiereWareNachDTO(Ware ware) {
        return this.warenKonverter.wareToDTO(ware);
    }
}
