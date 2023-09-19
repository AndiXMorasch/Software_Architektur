package de.hsos.swa.ssa.katalogVerwalten.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hsos.swa.ssa.katalogVerwalten.bl.VerwaltenKatalog;
import de.hsos.swa.ssa.katalogVerwalten.bl.Produktinformation;
import de.hsos.swa.ssa.katalogVerwalten.bl.Ware;

public class VerwaltenWarenRepository implements VerwaltenKatalog {

    private Connection connection;

    public VerwaltenWarenRepository() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/waren_db";
            String user = "sa_admin";
            String pw = "admin123";
            this.connection = DriverManager.getConnection(url, user, pw);
            //System.out.println("Connection successful!");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Vorsicht hier kann auch null zurückgegeben werden!
    @Override
    public Ware selectWare(long warennummer) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM waren WHERE warennummer=?")) {
            statement.setLong(1, warennummer);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                long warennummerTmp = resultSet.getLong("warennummer");
                String warenname = resultSet.getString("warenname");
                int preis = resultSet.getInt("preis");
                String beschreibung = resultSet.getString("beschreibung");
                Ware ware = new Ware(warennummerTmp, warenname, preis);
                Produktinformation produktinformation = new Produktinformation(beschreibung);
                ware.setProduktinformation(produktinformation);
                return ware;
            }
        }
        return null;
    }

    @Override
    public void createWare(Ware ware) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO waren(warennummer, warenname, preis, beschreibung) VALUES (?, ?, ?, ?)")) {
            statement.setLong(1, ware.getWarennummer());
            statement.setString(2, ware.getWarenname());
            statement.setInt(3, ware.getPreis());
            statement.setString(4, ware.getProduktinformation().getBeschreibung());
            statement.executeUpdate();
        }
    }

    @Override
    public void modifyWare(Ware ware) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement("UPDATE waren SET warenname = ?, preis = ?, beschreibung = ? WHERE warennummer = ?")) {
            statement.setString(1, ware.getWarenname());
            statement.setInt(2, ware.getPreis());
            statement.setString(3, ware.getProduktinformation().getBeschreibung());
            statement.setLong(4, ware.getWarennummer());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteWare(long warennummer) {
        try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM waren WHERE warennummer = ?")) {
            statement.setLong(1, warennummer);
            statement.executeUpdate();
            return;
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
}
