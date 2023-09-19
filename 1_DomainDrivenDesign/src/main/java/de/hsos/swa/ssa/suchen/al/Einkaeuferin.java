package de.hsos.swa.ssa.suchen.al;

import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.SuchAlgorithmus;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class Einkaeuferin implements SucheWare, PruefeWare, WaehleWare {
    private WareSuchen wareSuchen;
    private ProduktinformationHolen produktinformationHolen;
    private WarenkorbVerwalten warenkorbVerwalten;

    public Einkaeuferin(WareSuchen wareSuchen, ProduktinformationHolen produktinfo,
            WarenkorbVerwalten warenkorbVerwalten) {
        this.wareSuchen = wareSuchen;
        // Suchalgorithmus festlegen
        this.wareSuchen.legeSuchalgorithmusFest(SuchAlgorithmus.KEYWORDMATCHING);
        this.produktinformationHolen = produktinfo;
        this.warenkorbVerwalten = warenkorbVerwalten;
    }

    @Override
    public void wareZuWarenkorbHinzufuegen(Ware ware) {
        warenkorbVerwalten.wareHinzufuegen(warenkorbVerwalten.konvertiereWareNachDTO(ware));
    }

    @Override
    public Produktinformation holeDetailinformationen(Ware ware) {
        return produktinformationHolen.holeProduktinformation(ware);
    }

    @Override
    public List<Ware> sucheWare(String warenname) {
        return wareSuchen.wareSuchen(warenname);
    }

    @Override
    public Ware sucheWare(long warennummer) {
        return wareSuchen.wareSuchen(warennummer);
    }

}
