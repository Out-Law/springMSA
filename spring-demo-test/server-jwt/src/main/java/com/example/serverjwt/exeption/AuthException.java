package com.example.serverjwt.exeption;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
