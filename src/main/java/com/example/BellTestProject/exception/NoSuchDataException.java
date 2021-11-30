package com.example.BellTestProject.exception;

import javax.annotation.PostConstruct;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException(String message) {
        super(message);
    }
}
