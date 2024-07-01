package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static dbConnection instance;
    private Connection connection;

    private dbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/colthify", "root", "9090");
    }

    public Connection getConnection() {
        return connection;
    }

    public static synchronized dbConnection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new dbConnection();
        }
        return instance;
    }
}
