package com.epul.androidcinema.domain;

import java.io.Serializable;

public class LoginParam implements Serializable {
    private String email;
    private String password;

    public LoginParam(String email, String pwd) {
        this.email = email;
        this.password = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}