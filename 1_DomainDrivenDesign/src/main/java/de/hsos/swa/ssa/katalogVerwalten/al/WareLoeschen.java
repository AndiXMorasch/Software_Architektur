package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;

public class WareLoeschen {
    VerwaltenKatalog katalog;

    public WareLoeschen(VerwaltenKatalog katalog) {
        this.katalog = katalog;
    }

    public void applyDeletion(long warennummer) throws SQLException {
        this.katalog.deleteWare(warennummer);
    }
}
