package org.academiadecodigo.hackathon.screens.persistence;

/**
 * Created by codecadet on 17/03/17.
 */
public class UserModel {

    private Integer id;
    private String username;                // Username to login
    private String password;                // Password to login

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
