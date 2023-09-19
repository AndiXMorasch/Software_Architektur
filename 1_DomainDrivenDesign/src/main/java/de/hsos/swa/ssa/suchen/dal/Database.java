package de.hsos.swa.ssa.suchen.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton
class Database {
    private Connection connection;

    private static Database instance = null;

    public static Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private Database() throws SQLException, ClassNotFoundException {
        this.initDatabase();
    }

    public Connection getConnection() {
        return this.connection;
    }

    private void initDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/waren_db";
        String user = "sa_admin";
        String pw = "admin123";

        this.connection = DriverManager.getConnection(url, user, pw);
        // System.out.println("Connection successful!");
    }

}
