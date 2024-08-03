package com.example.demo.exceptions.authorization;

import com.example.demo.Dto.UnauthorizedErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static ObjectMapper OBJECT_MAPPER;
    public UserAuthenticationEntryPoint(){
        OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("auth message "+authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        System.out.println("response body "+authException.getClass().getsi);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), new UnauthorizedErrorDto("You shall not pass"));
    }
}
