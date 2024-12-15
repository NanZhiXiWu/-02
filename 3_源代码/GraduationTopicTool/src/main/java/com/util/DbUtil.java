package com.util;

import java.sql.*;

public class DbUtil {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private static  String url = "jdbc:mysql://localhost:3306/";
    private static  String username = "username";
    private static  String password = "password";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
