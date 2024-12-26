package com.khalilgayle.courtvisionserver.errorhandling;

public class TeamsNotFoundException extends RuntimeException {
    public TeamsNotFoundException(String message) {
        super(message);
    }
}
