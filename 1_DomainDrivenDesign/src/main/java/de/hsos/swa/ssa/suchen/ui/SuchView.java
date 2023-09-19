package de.hsos.swa.ssa.suchen.ui;

import java.util.List;
import java.util.Scanner;

import de.hsos.swa.ssa.suchen.bl.Ware;

class SuchView {
    private Scanner in;
    private SuchListener suchListener;

    SuchView() {
        this.in = new Scanner(System.in);
    }

    public void displayWarenCollection(List<Ware> warenList) {
        int i = 0;
        for (Ware ware : warenList) {
            System.out.println(i + ": " + ware);
            i++;
        }

        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print(
                        "" + "(0) Zurueck\n" + "(1) Ware nach Position wählen\n");
                eingabe = in.nextInt();
                switch (eingabe) {
                    case 0: {
                        onNewSearch();
                        return;
                    }
                    case 1: {
                        System.out.print("Position: ");
                        int pos = in.nextInt();
                        onWareSelected(pos);
                        return;
                    }
                    default: {
                        System.out.println("Das ist keine gültige Eingabe.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine valide Zahl an.");
                in.next();
            }
        }
    }

    public void displayNoResults() {
        System.out.println("Es wurden keine Suchergebnisse gefunden.");
        this.suchListener.onNewSearch();
    }

    public void onWareSelected(int pos) {
        this.suchListener.onWareSelected(pos);
    }

    public void onNewSearch() {
        this.suchListener.onNewSearch();
    }

    public void register(SuchListener suchListener) {
        this.suchListener = suchListener;
    }
}
