package de.hsos.swa.ssa.suchen.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.ssa.suchen.bl.Ware;

class KeywordMatching implements Warensuche {

    @Override
    public List<Ware> sucheWare(String suchbegriff) {
        try (PreparedStatement statement = Database.getInstance().getConnection()
                .prepareStatement("SELECT * FROM waren WHERE warenname = ?")) {
            statement.setString(1, suchbegriff);
            ResultSet resultSet = statement.executeQuery();
            return this.mapResultSetZuWaren(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Ware> mapResultSetZuWaren(ResultSet resultSet) throws SQLException {
        List<Ware> waren = new ArrayList<>();
        while (resultSet.next()) {
            long warennummer = resultSet.getLong("warennummer");
            String warenname = resultSet.getString("warenname");
            int preis = resultSet.getInt("preis");
            waren.add(new Ware(warennummer, warenname, preis));
        }
        return waren;
    }
}