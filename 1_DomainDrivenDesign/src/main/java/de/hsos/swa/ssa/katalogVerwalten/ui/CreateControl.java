package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.al.CreateWare;
import de.hsos.swa.ssa.katalogVerwalten.bl.Produktinformation;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class CreateControl implements CreateListener {

    private CreateView createView;
    private CreateControlListener createControlListener;
    private CreateWare createWare;

    private long warennummerTmp;
    private String warennameTmp;
    private int preisTmp;
    private String beschreibungTmp;

    public CreateControl(CreateWare createWare) {
        this.createView = new CreateView();
        this.createView.register(this);
        this.createWare = createWare;
    }

    void register(CreateControlListener createControlListener) {
        this.createControlListener = createControlListener;
    }

    void showView() throws SQLException {
        this.createView.showView();
    }

    @Override
    public void onWarennummer(long warennummer) {
        this.warennummerTmp = warennummer;
    }

    @Override
    public void onWarenname(String warenname) {
        this.warennameTmp = warenname;
    }

    @Override
    public void onPreis(int preis) {
        this.preisTmp = preis;
    }

    @Override
    public void onBeschreibung(String beschreibung) {
        this.beschreibungTmp = beschreibung;
    }

    @Override
    public void onConfirm() throws SQLException {
        Ware ware = new Ware(warennummerTmp, warennameTmp, preisTmp);
        Produktinformation produktinfo = new Produktinformation(beschreibungTmp);
        ware.setProduktinformation(produktinfo);
        this.createWare.createWare(ware);
        this.createControlListener.onCreationFinished();

    }

    @Override
    public void onCancel() {
        this.createControlListener.onCreationFinished();
    }
    
}
