package de.hsos.swa.ssa.suchen.ui;

import de.hsos.swa.ssa.suchen.al.PruefeWare;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class PruefControl implements PruefListener {
    private PruefView pruefControlView;
    private PruefControlListener pruefControlListener;
    private PruefeWare pruefeWare;
    private Ware ware;

    public PruefControl(PruefeWare pruefeWare) {
        this.pruefControlView = new PruefView();
        this.pruefControlView.register(this);
        this.pruefeWare = pruefeWare;
    }

    public void showView() {
        pruefControlView.dialog(ware);
    }

    public void register(PruefControlListener pruefControlListener) {
        this.pruefControlListener = pruefControlListener;
    }

    public void setWare(Ware ware) {
        this.ware = ware;
        Produktinformation produktinfo = this.pruefeWare.holeDetailinformationen(ware);
        this.ware.setProduktinformation(produktinfo);
    }

    @Override
    public void onConfirm() {
        this.pruefControlListener.onWareHinzufuegen(ware);
    }

    @Override
    public void onCancel() {
        this.pruefControlListener.onDisplayList();
    }
}
