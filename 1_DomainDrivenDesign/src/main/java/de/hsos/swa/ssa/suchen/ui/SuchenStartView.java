package de.hsos.swa.ssa.suchen.ui;

import java.util.Scanner;

class SuchenStartView {

    private SuchenStartListener suchenStartListener;
    private Scanner in;

    SuchenStartView() {
        this.in = new Scanner(System.in);
    }

    public void showView() {
        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print(
                        "" + "(0) Programm beenden\n" + "(1) Warennummer eingeben\n" + "(2) Warenbezeichnung eingeben\n");
                eingabe = in.nextInt();

                switch (eingabe) {
                    case 0: {
                        onClose();
                        return;
                    }
                    case 1: {
                        System.out.print("Geben Sie Ihre Warennummer ein: ");
                        int warennummer = in.nextInt();
                        onWarennummerInput(warennummer);
                        return;
                    }
                    case 2: {
                        System.out.print("Geben Sie Ihre Warenbezeichnung ein: ");
                        String warenname = in.next();
                        onWarennameInput(warenname);
                        return;
                    }
                    default: {
                        System.out.println("Das ist keine gültige Eingabe." + "\n");
                    }
                }
            } catch (Exception e){
                System.out.println("Bitte geben Sie eine valide Zahl ein.");
                in.next();
            }
        }
    }

    public void register(SuchenStartListener suchenStartListener) {
        this.suchenStartListener = suchenStartListener;
    }

    public void onClose() {
        this.suchenStartListener.onClose();
    }

    public void onWarennameInput(String warenname) {
        this.suchenStartListener.onWarennameInput(warenname);
    }

    public void onWarennummerInput(long warennummer) {
        this.suchenStartListener.onWarennummerInput(warennummer);
    }
}
