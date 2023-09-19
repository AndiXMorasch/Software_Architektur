package de.hsos.swa.ssa.suchen.ui;

import java.util.Scanner;

import de.hsos.swa.ssa.suchen.bl.Ware;

class PruefView {
    private Scanner in;
    private PruefListener pruefListener;

    PruefView() {
        this.in = new Scanner(System.in);
    }

    public void dialog(Ware ware) {
        System.out.println(ware);
        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print("" + "(0) Zurueck\n" + "(1) Ware zum Warenkorb hinzufuegen\n");
                eingabe = in.nextInt();
                switch (eingabe) {
                    case 1: {
                        onConfirm();
                        return;
                    } default: {
                        onCancel();
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine valide Zahl an.");
                in.next();
            }
        }
    }

    public void onConfirm() {
        this.pruefListener.onConfirm();
    }

    public void onCancel() {
        this.pruefListener.onCancel();
    }

    public void register(PruefListener pruefListener) {
        this.pruefListener = pruefListener;
    }
}
