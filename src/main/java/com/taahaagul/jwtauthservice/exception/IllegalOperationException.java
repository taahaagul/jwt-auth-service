package com.taahaagul.jwtauthservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalOperationException extends RuntimeException{

    public IllegalOperationException(String message) {
        super(message);
    }
}
