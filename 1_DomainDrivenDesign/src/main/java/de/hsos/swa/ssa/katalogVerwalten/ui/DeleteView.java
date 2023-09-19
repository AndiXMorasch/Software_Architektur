package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class DeleteView {
    private DeleteListener deleteListener;
    private Scanner in;

    public DeleteView() {
        this.in = new Scanner(System.in);
    }

    void register(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void showView() throws SQLException {
        Ware ware = selectWare();
        

        System.out.println("\n" + "Möchten Sie " + "(" + ware.getWarennummer() + ") " + ware.getWarenname() + " wirklich loeschen? (y/n)");
        String decision = in.next();
        
        if(decision.equals("y")) {
            System.out.println("\n" + "Ware erfolgreich geloescht.");
            this.deleteListener.onConfirm();
        } else {
            this.deleteListener.onCancel();
        }
    }

    private Ware selectWare() throws SQLException {
        while(true) {
            try {
                System.out.print("Warennummer: ");
                long warennummer = in.nextLong();
                Ware ware = this.deleteListener.onSelectDelete(warennummer);
                if(ware == null) {
                    System.out.println("Diese Warennummer existiert nicht. Versuchen Sie es erneut...");           
                } else {
                    System.out.println("Warendetails: ");
                    System.out.println(ware);
                    return ware;
                }
            } catch (InputMismatchException e) {
                System.out.println("Bitte geben Sie eine Ganzzahl ein");
                in.next();
            }
        }
    }
}
