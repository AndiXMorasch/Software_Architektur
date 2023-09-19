package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;

public class VerwaltenStartControl implements VerwaltenStartListener, CreateControlListener, ModifyControlListener, DeleteControlListener {

    VerwaltenFinishedListener verwaltenFinishedListener;
    VerwaltenStartView verwaltenStartView;
    CreateControl createControl;
    ModifyControl modifyControl;
    DeleteControl deleteControl;

    public VerwaltenStartControl(CreateControl createControl, ModifyControl modifyControl, DeleteControl deleteControl) {
        this.verwaltenStartView = new VerwaltenStartView();
        this.verwaltenStartView.register(this);
        this.createControl = createControl;
        this.createControl.register(this);
        this.modifyControl = modifyControl;
        this.modifyControl.register(this);
        this.deleteControl = deleteControl;
        this.deleteControl.register(this);
    }

    public void register(VerwaltenFinishedListener verwaltenFinishedListener) {
        this.verwaltenFinishedListener = verwaltenFinishedListener;
    }

    public void showMainView() {
        this.verwaltenStartView.showView();
    }

    @Override
    public void onClose() {
        this.verwaltenFinishedListener.onVerwaltenFinished();
    }

    @Override
    public void onCreate() throws SQLException {
        this.createControl.showView();
    }

    @Override
    public void onModify() throws SQLException {
        this.modifyControl.showView();
    }

    @Override
    public void onDelete() throws SQLException {
        this.deleteControl.showView();
    }

    @Override
    public void onDeleteFinished() {
        showMainView();
    }

    @Override
    public void onModificationFinished() {
        showMainView();
    }

    @Override
    public void onCreationFinished() {
        showMainView();
    }
    
}
