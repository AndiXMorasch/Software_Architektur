package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class WareBearbeiten {
    VerwaltenKatalog katalog;

    public WareBearbeiten(VerwaltenKatalog katalog) {
        this.katalog = katalog;
    }

    public void applyModification(Ware ware) throws SQLException {
        this.katalog.modifyWare(ware);
    }
}
