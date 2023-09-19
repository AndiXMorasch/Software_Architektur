package de.hsos.swa.ssa.suchen.ui;

public interface SuchenStartListener {
    public void onClose();

    public void onWarennameInput(String warenname);

    public void onWarennummerInput(long warennummer);
}
