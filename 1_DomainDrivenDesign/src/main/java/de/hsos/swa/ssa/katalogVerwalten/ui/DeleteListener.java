package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface DeleteListener {
    public Ware onSelectDelete(long warennummer) throws SQLException;
    public void onConfirm();
    public void onCancel();
}
