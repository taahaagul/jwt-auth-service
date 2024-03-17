package com.taahaagul.jwtauthservice.exception;

public class UserNotFoundException extends RuntimeException{

    public  UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
}
