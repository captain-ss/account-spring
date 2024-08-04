package com.example.demo.exceptions;

import com.example.demo.Dto.ExceptionResponseDto.ExceptionResponseDto;
import com.example.demo.exceptions.user.CredentialMismatchException;
import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.exceptions.user.UsernameAlreadyTakenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> payloadValidityException(MethodArgumentNotValidException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getMessage());
        ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        System.out.println("Invalid argument error "+e.getClass().getName());
        final HashMap<String, String> map=new HashMap<String, String>();
        for(FieldError fieldError: fieldErrors){
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            System.out.println(fieldError.getField()+" "+ fieldError.getDefaultMessage());
        }
        return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    public ResponseEntity<ExceptionResponseDto> usernameAlreadyTakenExceptionHandler(UsernameAlreadyTakenException e){
        return new ResponseEntity<ExceptionResponseDto>(new ExceptionResponseDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserEmailAlreadyTakenException.class)
    public ResponseEntity<ExceptionResponseDto> userEmailAlreadyTakenExceptionHandler(UserEmailAlreadyTakenException e){
        return new ResponseEntity<ExceptionResponseDto>(new ExceptionResponseDto(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CredentialMismatchException.class)
    public ResponseEntity<ExceptionResponseDto> CredentialMismatchExceptionHandler(CredentialMismatchException e){
        return new ResponseEntity<ExceptionResponseDto>(new ExceptionResponseDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
