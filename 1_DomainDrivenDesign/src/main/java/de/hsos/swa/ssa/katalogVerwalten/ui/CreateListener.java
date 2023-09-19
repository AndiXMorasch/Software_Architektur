package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

public interface CreateListener {
    public void onWarennummer(long warennummer);
    public void onWarenname(String warenname);
    public void onPreis(int preis);
    public void onBeschreibung(String beschreibung);
    public void onConfirm() throws SQLException;
    public void onCancel();
}
