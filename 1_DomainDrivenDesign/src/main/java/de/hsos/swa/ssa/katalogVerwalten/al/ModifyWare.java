package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface ModifyWare {
    public Ware select(long warennummer) throws SQLException;
    public void applyModification(Ware ware) throws SQLException;
}
