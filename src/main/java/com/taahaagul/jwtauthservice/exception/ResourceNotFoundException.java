package com.taahaagul.jwtauthservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName, MailException e) {
    }
}
