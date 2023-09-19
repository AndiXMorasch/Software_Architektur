package de.hsos.swa.ssa;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.al.VerwalterIn;
import de.hsos.swa.ssa.katalogVerwalten.al.WareAnlegen;
import de.hsos.swa.ssa.katalogVerwalten.al.WareAuswaehlen;
import de.hsos.swa.ssa.katalogVerwalten.al.WareBearbeiten;
import de.hsos.swa.ssa.katalogVerwalten.al.WareLoeschen;
import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;
import de.hsos.swa.ssa.katalogVerwalten.dal.VerwaltenWarenRepository;
import de.hsos.swa.ssa.katalogVerwalten.ui.CreateControl;
import de.hsos.swa.ssa.katalogVerwalten.ui.DeleteControl;
import de.hsos.swa.ssa.katalogVerwalten.ui.ModifyControl;
import de.hsos.swa.ssa.katalogVerwalten.ui.VerwaltenStartControl;

import de.hsos.swa.ssa.katalogVerwalten.ui.VerwaltenFinishedListener;
import de.hsos.swa.ssa.suchen.acl.MockWarenkorbAdapter;
import de.hsos.swa.ssa.suchen.acl.WarenkorbFuerSuche;
import de.hsos.swa.ssa.suchen.al.Einkaeuferin;
import de.hsos.swa.ssa.suchen.al.ProduktinformationHolen;
import de.hsos.swa.ssa.suchen.al.WareSuchen;
import de.hsos.swa.ssa.suchen.al.WarenkorbVerwalten;
import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.dal.WarenRepository;
import de.hsos.swa.ssa.suchen.ui.AuswahlControl;
import de.hsos.swa.ssa.suchen.ui.PruefControl;
import de.hsos.swa.ssa.suchen.ui.SearchFinishedListener;
import de.hsos.swa.ssa.suchen.ui.SuchControl;
import de.hsos.swa.ssa.suchen.ui.SuchenStartControl;

public class App implements SearchFinishedListener, VerwaltenFinishedListener {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        App app = new App();
        app.startSearch();
        // app.startVerwaltung();
    }

    public void startSearch() {
        Katalog katalog = new WarenRepository();
        WareSuchen wareSuchen = new WareSuchen(katalog);
        ProduktinformationHolen produkInfoHolen = new ProduktinformationHolen(katalog);
        WarenkorbFuerSuche warenkorbFuerSuche = new MockWarenkorbAdapter();
        WarenkorbVerwalten warenkorbVerwalten = new WarenkorbVerwalten(warenkorbFuerSuche);
        Einkaeuferin einkaeuferin = new Einkaeuferin(wareSuchen, produkInfoHolen, warenkorbVerwalten);
        SuchControl suchControl = new SuchControl(einkaeuferin);
        PruefControl pruefControl = new PruefControl(einkaeuferin);
        AuswahlControl auswahlControl = new AuswahlControl();
        SuchenStartControl suchenStartControl = new SuchenStartControl(suchControl, pruefControl, auswahlControl);
        suchenStartControl.register(this);
        suchenStartControl.onNewSearch();
    }

    public void startVerwaltung() {
        VerwaltenKatalog katalog = new VerwaltenWarenRepository();
        WareAnlegen wareAnlegen = new WareAnlegen(katalog);
        WareAuswaehlen WareAuswaehlen = new WareAuswaehlen(katalog);
        WareBearbeiten wareBearbeiten = new WareBearbeiten(katalog);
        WareLoeschen WareLoeschen = new WareLoeschen(katalog);
        VerwalterIn verwalterIn = new VerwalterIn(wareAnlegen, WareAuswaehlen, wareBearbeiten, WareLoeschen);
        CreateControl createControl = new CreateControl(verwalterIn);
        ModifyControl modifyControl = new ModifyControl(verwalterIn);
        DeleteControl deleteControl = new DeleteControl(verwalterIn);
        VerwaltenStartControl verwaltenStartControl = new VerwaltenStartControl(createControl, modifyControl,
                deleteControl);
        verwaltenStartControl.register(this);
        verwaltenStartControl.showMainView();
    }

    @Override
    public void onSearchFinished() {
        // Mache andere Sachen...
    }

    @Override
    public void onVerwaltenFinished() {
        // Mache andere Sachen...
    }
}
