package com.khalilgayle.courtvisionserver.errorhandling;

public class StatsNotFoundException extends RuntimeException {
    public StatsNotFoundException(String message) {
        super(message);
    }
}
