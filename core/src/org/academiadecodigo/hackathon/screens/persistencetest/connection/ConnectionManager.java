package org.academiadecodigo.hackathon.screens.persistencetest.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 17/03/17.
 */
public class ConnectionManager {

    private Connection connection = null;

    public Connection getConnection() {
        try {
            if( connection == null ) {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/indiana_gomes", "root", "");
            }
        } catch ( SQLException ex) {
            System.out.println("Failure to connect to database: " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if( connection != null ) {
                connection.close();
            }
        } catch ( SQLException ex ) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
