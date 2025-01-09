package com.khalilgayle.courtvisionserver.errorhandling;

public class InvalidQueryParametersException extends RuntimeException {
    public InvalidQueryParametersException(String message) {
        super(message);
    }
}
