package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

public interface VerwaltenStartListener {
    public void onClose();
    public void onCreate() throws SQLException;
    public void onModify() throws SQLException;
    public void onDelete() throws SQLException;
}
