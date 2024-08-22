package com.example.final_project.entities;

import java.util.List;

public class User {
    private String username;
    private int user_id;
    List<Account> accounts;
    private String password;

    //Constructor

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // default
    public User(){}

    //setter and getter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
