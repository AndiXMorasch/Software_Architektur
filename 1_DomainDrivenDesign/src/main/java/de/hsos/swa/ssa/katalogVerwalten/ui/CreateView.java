package de.hsos.swa.ssa.katalogVerwalten.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateView {
    private CreateListener createListener;
    private Scanner in;

    public CreateView() {
        this.in = new Scanner(System.in);
    }

    void register(CreateListener createListener) {
        this.createListener = createListener;
    }

    public void showView() throws SQLException {
        while(true){
            try {
                System.out.print("Warennummer: ");
                long warennummer = in.nextLong();
                this.createListener.onWarennummer(warennummer);
                break;
            } catch (InputMismatchException e){
                System.out.println("Bitte geben Sie eine Ganzzahl ein.");
                in.next();
            }
        }

        System.out.print("Warenname: ");
        String warenname = in.next();
        this.createListener.onWarenname(warenname);

        while(true){
            try {
                System.out.print("Preis: ");
                int preis = in.nextInt();
                this.createListener.onPreis(preis);
                break;
            } catch (InputMismatchException e){
                System.out.println("Bitte geben Sie eine Ganzzahl ein.");
                in.next();
            }
        }

        System.out.print("Beschreibung: ");
        in.nextLine();
        String beschreibung = in.nextLine();
        this.createListener.onBeschreibung(beschreibung);

        System.out.println("Möchten Sie den Artikel hinzufuegen? (y/n)");
        String decision = in.next();
        
        if(decision.equals("y")) {
            System.out.println("\n" + "Artikel erfolgreich hinzugefuegt.");
            this.createListener.onConfirm();
        } else {
            this.createListener.onCancel();
        }
        
    }
}
