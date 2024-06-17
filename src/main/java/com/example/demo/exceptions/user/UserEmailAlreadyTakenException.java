package com.example.demo.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User email already taken")
public class UserEmailAlreadyTakenException extends RuntimeException {
    private String message;

    public UserEmailAlreadyTakenException(){}
    public UserEmailAlreadyTakenException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
