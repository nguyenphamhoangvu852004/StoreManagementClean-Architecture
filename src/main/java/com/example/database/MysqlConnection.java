package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/StoreManager";
    private static final String USER = "root";
    private static final String PASSWORD = "hoang";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null||connection.isClosed()) {

            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new SQLException("Error connecting to database", e);
            }
        }
        return connection;
    }
}
