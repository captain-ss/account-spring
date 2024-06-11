package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1") // Can be used for API versioning
public class TestController {
    @GetMapping(path = "/")
    public String hello(){
        return "Hello world";
    }
    @GetMapping(path = "hello")
    public String hi(){
        return "HELLO";
    }
}
