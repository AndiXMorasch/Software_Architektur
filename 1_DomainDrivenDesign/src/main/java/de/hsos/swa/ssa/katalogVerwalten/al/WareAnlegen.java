package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class WareAnlegen {
    VerwaltenKatalog katalog;

    public WareAnlegen(VerwaltenKatalog katalog) {
        this.katalog = katalog;
    }

    public void createWare(Ware ware) throws SQLException {
        this.katalog.createWare(ware);
    }
}
