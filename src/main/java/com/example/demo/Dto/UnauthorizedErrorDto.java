package com.example.demo.Dto;

public class UnauthorizedErrorDto {
    private final String message;
    public UnauthorizedErrorDto(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
