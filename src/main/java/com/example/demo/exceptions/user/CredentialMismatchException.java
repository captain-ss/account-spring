package com.example.demo.exceptions.user;

import com.example.demo.exceptions.ExceptionBaseClass;

public class CredentialMismatchException extends ExceptionBaseClass {
    public CredentialMismatchException(String message){
        super(message);
    }
}
