package com.example.demo.Dto.ExceptionResponseDto;

public class ExceptionResponseDto {
    private final String message;
    public ExceptionResponseDto(String message){
        this.message=message;
    }
    public String getMessage(){
        return this.message;
    }
}
