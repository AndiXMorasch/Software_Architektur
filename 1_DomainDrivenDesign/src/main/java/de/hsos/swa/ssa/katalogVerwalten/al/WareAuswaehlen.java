package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class WareAuswaehlen {
    VerwaltenKatalog katalog;

    public WareAuswaehlen(VerwaltenKatalog katalog) {
        this.katalog = katalog;
    }

    public Ware selectWare(long warennummer) throws SQLException {
        return this.katalog.selectWare(warennummer);
    }
}
