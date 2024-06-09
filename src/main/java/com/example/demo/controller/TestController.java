package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/Test")
public class TestController {  
    @GetMapping()
    public String getTestString() {  
        return "hello there";  
    }
}