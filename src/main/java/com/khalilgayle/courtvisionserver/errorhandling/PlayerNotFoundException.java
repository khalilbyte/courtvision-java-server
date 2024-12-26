package com.khalilgayle.courtvisionserver.errorhandling;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
