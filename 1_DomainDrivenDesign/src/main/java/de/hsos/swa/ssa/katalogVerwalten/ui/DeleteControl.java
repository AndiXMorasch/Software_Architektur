package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.al.DeleteWare;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class DeleteControl implements DeleteListener {

    private DeleteWare deleteWare;
    private DeleteView deleteView;
    private DeleteControlListener deleteControlListener;

    private long warennummerTmp;

    public DeleteControl(DeleteWare deleteWare) {
        this.deleteView = new DeleteView();
        this.deleteView.register(this);
        this.deleteWare = deleteWare;
    }

    void register(DeleteControlListener deleteControlListener) {
        this.deleteControlListener = deleteControlListener;
    }

    void showView() throws SQLException {
        this.deleteView.showView();
    }

    @Override
    public Ware onSelectDelete(long warennummer) throws SQLException {
        Ware ware = this.deleteWare.select(warennummer);
        if(ware != null) {
            this.warennummerTmp = ware.getWarennummer();
        }
        return ware;
    }

    @Override
    public void onConfirm() {
        this.deleteWare.applyDeletion(this.warennummerTmp);
    }

    @Override
    public void onCancel() {
        this.deleteControlListener.onDeleteFinished();
    }
    
}
