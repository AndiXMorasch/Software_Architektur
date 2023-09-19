package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public interface CreateWare {
    public void createWare(Ware ware) throws SQLException;
}
