package com.example.demo.exceptions.user;

import com.example.demo.exceptions.ExceptionBaseClass;

public class UnauthorizedRequestException extends ExceptionBaseClass {
    public UnauthorizedRequestException(String message){
        super(message);
    }
}
