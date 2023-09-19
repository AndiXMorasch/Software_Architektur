package de.hsos.swa.ssa.katalogVerwalten.al;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class VerwalterIn implements CreateWare, ModifyWare, DeleteWare {

    private WareAnlegen wareAnlegen;
    private WareAuswaehlen wareAuswaehlen;
    private WareBearbeiten wareBearbeiten;
    private WareLoeschen wareLoeschen;

    public VerwalterIn(WareAnlegen wareAnlegen, WareAuswaehlen wareAuswaehlen, WareBearbeiten wareBearbeiten, WareLoeschen wareLoeschen) {
        this.wareAnlegen = wareAnlegen;
        this.wareAuswaehlen = wareAuswaehlen;
        this.wareBearbeiten = wareBearbeiten;
        this.wareLoeschen = wareLoeschen;
    }

    @Override
    public void applyDeletion(long warennummer) {
        try {
            this.wareLoeschen.applyDeletion(warennummer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ware select(long warennummer) throws SQLException {
        return this.wareAuswaehlen.selectWare(warennummer);
    }

    @Override
    public void applyModification(Ware ware) throws SQLException {
        this.wareBearbeiten.applyModification(ware);
    }

    @Override
    public void createWare(Ware ware) throws SQLException {
        this.wareAnlegen.createWare(ware);
    }
    
}
