package de.hsos.swa.ssa.suchen.ui;

public class AuswahlControl implements AuswahlListener {
    private AuswahlView auswahlControlView;
    private AuswahlControlListener auswahlControlListener;

    public AuswahlControl() {
        this.auswahlControlView = new AuswahlView();
        this.auswahlControlView.register(this);
    }

    public void showView() {
        auswahlControlView.dialog();
    }

    public void register(AuswahlControlListener auswahlControlListener) {
        this.auswahlControlListener = auswahlControlListener;
    }

    @Override
    public void onAcknowledgement() {
        this.auswahlControlListener.onWareHinzufuegenFinished();
    }
}
