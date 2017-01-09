package com.sand.taskmanager;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Date;

/**
 * Created by Biguang on 2017/1/9.
 */

public class User implements Serializable {
    private int id;
    private String userName;
    private String password;
    private Date registerDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String USERNAME = "userName";
    public static String PASSWORD = "password";

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        registerDate = new Date();
    }
}