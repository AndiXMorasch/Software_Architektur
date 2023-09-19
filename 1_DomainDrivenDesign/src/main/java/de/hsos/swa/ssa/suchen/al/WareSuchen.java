package de.hsos.swa.ssa.suchen.al;

import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.bl.SuchAlgorithmus;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class WareSuchen {

    private Katalog katalog;

    public WareSuchen(Katalog katalog) {
        this.katalog = katalog;
    }

    void legeSuchalgorithmusFest(SuchAlgorithmus algo) {
        this.katalog.legeSuchalgorithmusFest(algo);
    }

    List<Ware> wareSuchen(String warenname) {
        List<Ware> waren =  this.katalog.suchen(warenname);
        if(waren.isEmpty()){
            this.katalog.legeSuchalgorithmusFest(SuchAlgorithmus.SEMANTICMATCHING);
            waren = this.katalog.suchen(warenname);
            this.katalog.legeSuchalgorithmusFest(SuchAlgorithmus.KEYWORDMATCHING);
        }
        return waren;
    }

    Ware wareSuchen(long warennummer) {
        return this.katalog.suchen(warennummer);
    }
}
