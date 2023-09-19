package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.util.Scanner;

public class VerwaltenStartView {
    private VerwaltenStartListener verwaltenStartListener;
    private Scanner in;

    public VerwaltenStartView() {
        this.in = new Scanner(System.in);
    }

    void register(VerwaltenStartListener verwaltenStartListener) {
        this.verwaltenStartListener = verwaltenStartListener;
    }

    public void showView() {
        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print(
                        "" + "(0) Programm beenden\n" + "(1) Ware anlegen\n" + "(2) Ware ändern\n" + "(3) Ware löschen\n");
                eingabe = in.nextInt();
                switch (eingabe) {
                    case 0: {
                        this.verwaltenStartListener.onClose();
                        return;
                    }
                    case 1: {
                        this.verwaltenStartListener.onCreate();
                        return;
                    }
                    case 2: {
                        this.verwaltenStartListener.onModify();
                        return;
                    }
                    case 3: {
                        this.verwaltenStartListener.onDelete();
                        return;
                    }
                    default: {
                        System.out.println("Das ist keine gültige Eingabe." + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine valide Zahl ein.");
                in.next();
            }
        }
    }
}
