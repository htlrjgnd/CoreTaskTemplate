package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static Connection getMySqlConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "mysql";
        String userName = "root";
        String password = "root";

        return getMySqlConnection(hostName, dbName, userName, password);
    }

    private static Connection getMySqlConnection(String hostName, String dbName,
                                                 String userName, String password)
            throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    public static Statement getStatement() {
        try {
            return getMySqlConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
