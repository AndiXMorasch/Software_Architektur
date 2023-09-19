package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface DeleteWare {
    public Ware select(long warennummer) throws SQLException;
    public void applyDeletion(long warennummer);
}
