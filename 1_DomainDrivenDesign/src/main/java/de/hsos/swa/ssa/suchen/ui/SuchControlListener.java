package de.hsos.swa.ssa.suchen.ui;

import de.hsos.swa.ssa.suchen.bl.Ware;

public interface SuchControlListener {
    public void onWareSelected(Ware ware);

    public void onNewSearch();
}
