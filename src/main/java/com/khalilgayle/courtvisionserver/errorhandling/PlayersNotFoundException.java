package com.khalilgayle.courtvisionserver.errorhandling;

public class PlayersNotFoundException extends RuntimeException {
    public PlayersNotFoundException(String message) {
        super(message);
    }
}
