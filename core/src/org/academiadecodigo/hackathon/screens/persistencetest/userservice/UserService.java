package org.academiadecodigo.hackathon.screens.persistencetest.userservice;

import org.academiadecodigo.hackathon.screens.persistencetest.UserModel;

/**
 * Created by codecadet on 17/03/17.
 */
public interface UserService {

    boolean authenticate(String name, String password);                     // Authenticates user login

    boolean addUser(UserModel user);                    // Adds a new user to the database

    UserModel findByName(String name);               // Finds a user by name

}
