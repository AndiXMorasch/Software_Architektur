package de.hsos.swa.ssa.suchen.ui;

import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.ssa.suchen.al.SucheWare;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class SuchControl implements SuchListener {
    private SuchView suchControlView;
    private List<Ware> warenListe;
    private SucheWare warensuche;
    private SuchControlListener suchControlListener;

    public SuchControl(SucheWare sucheWare) {
        this.suchControlView = new SuchView();
        this.suchControlView.register(this);
        this.warensuche = sucheWare;
    }

    public void sucheWare(String warenname) {
        this.warenListe = this.warensuche.sucheWare(warenname);
        if (warenListe.isEmpty()) {
            displayNoResults();
        } else {
            displayWarenCollection();
        }
    }

    public void sucheWare(long warennummer) {
        Ware ware = this.warensuche.sucheWare(warennummer);
        if (ware == null) {
            displayNoResults();
        } else {
            this.warenListe = new ArrayList<Ware>();
            this.warenListe.add(ware);
            displayWarenCollection();
        }
    }

    private void displayWarenCollection() {
        this.suchControlView.displayWarenCollection(warenListe);
    }

    private void displayNoResults() {
        this.suchControlView.displayNoResults();
    }

    public void register(SuchControlListener suchControlListener) {
        this.suchControlListener = suchControlListener;
    }

    public void onWareSelected(int pos) {
        Ware ware = warenListe.get(pos);
        this.suchControlListener.onWareSelected(ware);
    }

    public void displayLastResults() {
        this.displayWarenCollection();
    }

    @Override
    public void onNewSearch() {
        this.suchControlListener.onNewSearch();
    }
}