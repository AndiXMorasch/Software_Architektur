package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class ModifyView {
    ModifyListener modifyListener;
    Scanner in;

    public ModifyView() {
        this.in = new Scanner(System.in);
    }

    void register(ModifyListener modifyListener) {
        this.modifyListener = modifyListener;
    }

    public void showView() throws SQLException {
        selectWare();

        int eingabe = -1;
        while (eingabe != 0) {
            try {
                System.out.print(
                        "" + "(0) Abbrechen\n" + "(1) Neuer Warenname\n" + "(2) Neuer Preis\n" + "(3) Neue Beschreibung\n" + "(4) Änderungen bestätigen\n");
                eingabe = in.nextInt();
                switch (eingabe) {
                    case 0: {
                        this.modifyListener.onCancel();
                        break;
                    }
                    case 1: {
                        System.out.print("Warenname: ");
                        String neuerWarenname = in.next();
                        this.modifyListener.onWarenname(neuerWarenname);
                        break;
                    }
                    case 2: {
                        System.out.print("Preis: ");
                        int neuerPreis = in.nextInt();
                        this.modifyListener.onPreis(neuerPreis);
                        break;
                    }
                    case 3: {
                        System.out.print("Beschreibung: ");
                        in.nextLine();
                        String neueBeschreibung = in.nextLine();
                        this.modifyListener.onBeschreibung(neueBeschreibung);
                        break;
                    }
                    case 4: {
                        System.out.println("\n" + "Änderungen erfolgreich durchgeführt.");
                        this.modifyListener.onConfirm();
                        break;
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

    private void selectWare() throws SQLException {
        while(true) {
            try {
                System.out.print("Warennummer: ");
                long warennummer = in.nextLong();
                Ware ware = this.modifyListener.onWareSelect(warennummer);
                if(ware == null) {
                    System.out.println("Diese Warennummer existiert nicht. Versuchen Sie es erneut...");
                } else {
                    System.out.println("Warendetails: ");
                    System.out.println(ware);
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Bitte geben Sie eine Ganzzahl ein");
                in.next();
            }
        }
    }
}
