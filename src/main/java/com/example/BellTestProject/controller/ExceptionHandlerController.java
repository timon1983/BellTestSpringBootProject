package com.example.BellTestProject.controller;

import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.view.ResponseError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchDataException.class)
    public ResponseError handleNotException(NoSuchDataException e){
        ResponseError responseData = new ResponseError(e.getMessage());
        return responseData;
    }
}
