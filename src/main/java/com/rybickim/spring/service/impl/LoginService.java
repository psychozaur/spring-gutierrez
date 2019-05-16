package com.rybickim.spring.service.impl;

import com.rybickim.spring.service.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService implements Login {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private String username;
    private String password;

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

    @Override
    public boolean isAuthorized(String email, String pass) {
        logger.debug("isAuthorized(), email: " + email + ", pass: " + pass);

        if(username.equals(email) && password.equals(pass)) return true;
        return false;
    }
}
