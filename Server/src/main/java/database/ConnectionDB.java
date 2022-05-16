package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB connectionDB_instance = null;
    final private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    final private String user = "lorenzo";
    final private String pass = "LORENZO";
    public Connection connection;

    private ConnectionDB() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ConnectionDB getInstance() {
        if (connectionDB_instance == null) {
            connectionDB_instance = new ConnectionDB();
        }
        return connectionDB_instance;
    }
}
