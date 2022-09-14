package com.example.demo.Auth;

import java.io.Serializable;

public class TokenRequest implements Serializable {
    private static final long serialVersionUID = -5616176897013108345L;

    private String email;
    private String password;

    public TokenRequest() {
        super();
    }

    public TokenRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
