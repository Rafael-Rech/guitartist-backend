package com.tcc.tcc.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tcc.tcc.domain.exception.ResourceNotFoundException;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UnsuccessfulRequestException.class)
    public ResponseEntity<?> exceptionHandler(UnsuccessfulRequestException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception exception){
        return new ResponseEntity<>("Internal server error",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
