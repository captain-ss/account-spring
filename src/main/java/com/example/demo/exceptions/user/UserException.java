package com.example.demo.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exists")
public class UserException extends RuntimeException {
    private String message;

    public UserException(){}
    public UserException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
