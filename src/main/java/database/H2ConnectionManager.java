package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionManager {
    private static final String URL = "jdbc:h2:./database";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static Connection connection;

    private H2ConnectionManager() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to H2 database.");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to H2 database.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("H2 connection closed.");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close the H2 database.");
            }
        }
    }
}