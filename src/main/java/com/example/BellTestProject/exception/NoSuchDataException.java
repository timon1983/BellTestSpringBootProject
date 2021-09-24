package com.example.BellTestProject.exception;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException(String message) {
        super(message);
    }
}
