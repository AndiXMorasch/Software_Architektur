package de.hsos.swa.ssa.suchen.acl;

import java.util.ArrayList;
import java.util.Collection;

public class MockWarenkorbAdapter implements WarenkorbFuerSuche {

    private Collection<WareDTO> warenList;

    public MockWarenkorbAdapter() {
        this.warenList = new ArrayList<>();
    }

    @Override
    public void wareHinzufuegen(WareDTO ware) {
        this.warenList.add(ware);
    }
}
