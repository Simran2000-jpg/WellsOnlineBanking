package com.onlinebanking.team3.onlinebanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InsufficientBalanceException extends RuntimeException{
    //
    public InsufficientBalanceException(String message) {
        super(message);
    }
}


