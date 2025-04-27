package com.tcc.tcc.domain.exception;

public class UnsuccessfulRequestException extends RuntimeException {
    public UnsuccessfulRequestException(String message){
        super(message);
    }
}
