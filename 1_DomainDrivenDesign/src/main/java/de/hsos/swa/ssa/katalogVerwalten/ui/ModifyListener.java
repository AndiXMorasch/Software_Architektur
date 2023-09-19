package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface ModifyListener {
    public Ware onWareSelect(long warennummer) throws SQLException;
    public void onWarenname(String warenname);
    public void onPreis(int preis);
    public void onBeschreibung(String beschreibung);
    public void onConfirm() throws SQLException;
    public void onCancel();
}
