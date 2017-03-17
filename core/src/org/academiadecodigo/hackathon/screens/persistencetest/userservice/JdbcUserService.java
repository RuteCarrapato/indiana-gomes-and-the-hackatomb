package org.academiadecodigo.hackathon.screens.persistencetest.userservice;

/**
 * Created by codecadet on 17/03/17.
 */

import org.academiadecodigo.hackathon.screens.persistencetest.connection.ConnectionManager;
import org.academiadecodigo.hackathon.screens.persistencetest.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserService implements UserService {

    Connection dbConnection;

    public JdbcUserService() {
        ConnectionManager db = new ConnectionManager();

        dbConnection = db.getConnection();
    }

    @Override
    public boolean authenticate(String name, String password) {
        try {

            String query = "SELECT user_name, user_password FROM users WHERE user_name=? AND user_password=?";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, name);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {

                if(resultSet.getString("username").equals(name) && resultSet.getString("password").equals(password)) {
                    System.out.println("ENTERED AUTHENTICATED!");
                    return true;
                }
            }

            return false;

        } catch(SQLException ex) {
            System.out.println("Error authenticating user: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public boolean addUser(UserModel user) {
        return true;
    }

    @Override
    public UserModel findByName(String name) {

        UserModel user = null;

        try {
            String query = "SELECT user_name, user_password FROM users WHERE user_name = ?";
            PreparedStatement statement = dbConnection.prepareStatement(query);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {

                String usernameValue = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");

            }
        } catch(SQLException ex) {
            System.out.println("Error finding by name in DBConnection " + ex.getMessage());
        }

        return user;
    }

}
