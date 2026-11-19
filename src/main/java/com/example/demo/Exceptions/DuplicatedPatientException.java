package com.example.demo.Exceptions;

public class DuplicatedPatientException extends RuntimeException {
    public DuplicatedPatientException(String message) {
        super(message);
    }
}
