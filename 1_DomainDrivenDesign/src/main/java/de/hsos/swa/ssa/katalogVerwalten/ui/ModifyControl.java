package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.al.ModifyWare;
import de.hsos.swa.ssa.katalogVerwalten.bl.Produktinformation;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class ModifyControl implements ModifyListener {

    private ModifyWare modifyWare;
    private ModifyView modifyView;
    private ModifyControlListener modifyControlListener;

    private long warennummerTmp;
    private String warennameTmp;
    private int preisTmp;
    private String beschreibungTmp;

    public ModifyControl(ModifyWare modifyWare) {
        this.modifyView = new ModifyView();
        this.modifyView.register(this);
        this.modifyWare = modifyWare;
    }

    void register(ModifyControlListener modifyControlListener) {
        this.modifyControlListener = modifyControlListener;
    }

    void showView() throws SQLException {
        this.modifyView.showView();
    }

    @Override
    public Ware onWareSelect(long warennummer) throws SQLException {
        Ware ware = this.modifyWare.select(warennummer);
        if(ware != null) {
            this.warennummerTmp = ware.getWarennummer();
            this.warennameTmp = ware.getWarenname();
            this.preisTmp = ware.getPreis();
            this.beschreibungTmp = ware.getProduktinformation().getBeschreibung();
        }
        return ware;
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
        Produktinformation produktinformation = new Produktinformation(beschreibungTmp);
        ware.setProduktinformation(produktinformation);
        this.modifyWare.applyModification(ware);
        this.modifyControlListener.onModificationFinished();
    }

    @Override
    public void onCancel() {
        this.modifyControlListener.onModificationFinished();
    }
    
}
