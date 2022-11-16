package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/players_db";
    static final String USER = "postgres";
    static final String PASS = "1488";

    static Connection connection = null;

    public BaseConnection() {

    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
