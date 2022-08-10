package io.takima.sgef.stringcalculator;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException(String errorMessage) {
        super(errorMessage);
    }
}
