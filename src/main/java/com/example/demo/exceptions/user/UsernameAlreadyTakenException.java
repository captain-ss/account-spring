package com.example.demo.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username already in use")
public class UsernameAlreadyTakenException extends RuntimeException{
    private final String message;
    public UsernameAlreadyTakenException(String message){
        super(message);
        this.message=message;
    }

    public String getMessage(){
        return  message;
    }
}
