package de.hsos.swa.ssa.suchen.ui;

import java.util.Scanner;

class AuswahlView {
    private Scanner in;
    private AuswahlListener auswahlListener;

    AuswahlView() {
        this.in = new Scanner(System.in);
    }

    public void dialog() {
        System.out.println("Die Ware wurde erfolgreich dem Warenkorb hinzugefuegt.");
        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print("" + "(0) OK\n");
                eingabe = in.nextInt();
                switch (eingabe) {
                    default: {
                        onAcknowledgement();
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine valide Zahl an.");
                in.next();
            }
        }
    }

    public void register(AuswahlListener auswahlListener) {
        this.auswahlListener = auswahlListener;
    }

    private void onAcknowledgement() {
        this.auswahlListener.onAcknowledgement();
    }
}
