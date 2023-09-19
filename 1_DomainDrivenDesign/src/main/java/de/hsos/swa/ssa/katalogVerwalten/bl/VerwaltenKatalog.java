package de.hsos.swa.ssa.katalogVerwalten.bl;

import java.sql.SQLException;

public interface VerwaltenKatalog {
    public Ware selectWare(long warennummer) throws SQLException;
    public void createWare(Ware ware) throws SQLException;
    public void modifyWare(Ware ware) throws SQLException;
    public void deleteWare(long warennummer);
}
