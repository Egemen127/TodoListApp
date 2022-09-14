package com.example.demo.Auth;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        System.out.println("Error");
    }
}
