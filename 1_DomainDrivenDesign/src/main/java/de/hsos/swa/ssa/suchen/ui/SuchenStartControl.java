package de.hsos.swa.ssa.suchen.ui;

import de.hsos.swa.ssa.suchen.bl.Ware;

public class SuchenStartControl
        implements SuchenStartListener, SuchControlListener, PruefControlListener, AuswahlControlListener {
    private SuchenStartView suchenStartView;
    private SuchControl suchControl;
    private PruefControl pruefControl;
    private AuswahlControl auswahlControl;
    private SearchFinishedListener searchFinishedListener;

    public SuchenStartControl(SuchControl suchControl, PruefControl pruefControl, AuswahlControl auswahlControl) {
        this.suchenStartView = new SuchenStartView();
        this.suchenStartView.register(this);
        this.suchControl = suchControl;
        this.suchControl.register(this);
        this.pruefControl = pruefControl;
        this.pruefControl.register(this);
        this.auswahlControl = auswahlControl;
        this.auswahlControl.register(this);
    }

    public void register(SearchFinishedListener searchFinishedListener) {
        this.searchFinishedListener = searchFinishedListener;
    }

    @Override
    public void onWareHinzufuegenFinished() {
        this.onNewSearch();
    }

    @Override
    public void onWareHinzufuegen(Ware ware) {
        this.auswahlControl.showView();
    }

    @Override
    public void onDisplayList() {
        this.suchControl.displayLastResults();
    }

    @Override
    public void onWareSelected(Ware ware) {
        this.pruefControl.setWare(ware);
        this.pruefControl.showView();
    }

    @Override
    public void onNewSearch() {
        this.suchenStartView.showView();
    }

    @Override
    public void onClose() {
        this.searchFinishedListener.onSearchFinished();
    }

    @Override
    public void onWarennameInput(String warenname) {
        this.suchControl.sucheWare(warenname);
    }

    @Override
    public void onWarennummerInput(long warennummer) {
        this.suchControl.sucheWare(warennummer);
    }

}
