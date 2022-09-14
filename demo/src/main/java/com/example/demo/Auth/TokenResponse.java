package com.example.demo.Auth;

import java.io.Serializable;

public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    private final String currentUser;

    public TokenResponse(String token, String currentUser) {
        this.token = token;
        this.currentUser = currentUser;
    }

    public String getToken() {
        return this.token;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }
    
}
