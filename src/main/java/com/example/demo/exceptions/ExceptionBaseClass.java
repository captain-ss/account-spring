package com.example.demo.exceptions;

public class ExceptionBaseClass extends RuntimeException{
    private final String message;
    public ExceptionBaseClass(String message){
        this.message = message;
    };

    public String getMessage() {
        return message;
    }
}
