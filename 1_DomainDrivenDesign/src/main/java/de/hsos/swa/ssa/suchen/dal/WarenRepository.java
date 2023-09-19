package de.hsos.swa.ssa.suchen.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Katalog;
import de.hsos.swa.ssa.suchen.bl.Produktinformation;
import de.hsos.swa.ssa.suchen.bl.SuchAlgorithmus;
import de.hsos.swa.ssa.suchen.bl.Ware;

public class WarenRepository implements Katalog {
    private Warensuche warensucheImpl;

    private static WarenRepository instance = null;

    public static WarenRepository getInstance() {
        if (instance == null) {
            instance = new WarenRepository();
        }
        return instance;
    }

    @Override
    public void legeSuchalgorithmusFest(SuchAlgorithmus algo) {
        if (algo.equals(SuchAlgorithmus.KEYWORDMATCHING)) {
            this.warensucheImpl = new KeywordMatching();
        } else {
            this.warensucheImpl = new SemanticMatching();
        }
    }

    @Override
    public List<Ware> suchen(String warenname) {
        return this.warensucheImpl.sucheWare(warenname);
    }

    // Vorsicht hier kann auch null zurückgegeben werden!
    @Override
    public Ware suchen(long warennummer) {
        try (PreparedStatement statement = Database.getInstance().getConnection()
                .prepareStatement("SELECT * FROM waren WHERE warennummer = ?")) {
            statement.setLong(1, warennummer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() != false) {
                String warenname = resultSet.getString("warenname");
                int preis = resultSet.getInt("preis");
                return new Ware(warennummer, warenname, preis);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Vorsicht hier kann auch null zurückgegeben werden!
    @Override
    public Produktinformation gebeProduktinformation(Ware ware) {
        try (PreparedStatement statement = Database.getInstance().getConnection()
                .prepareStatement("SELECT beschreibung FROM waren WHERE warennummer = ?")) {
            statement.setLong(1, ware.getWarennummer());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() != false) {
                String beschreibung = resultSet.getString("beschreibung");
                return new Produktinformation(beschreibung);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
