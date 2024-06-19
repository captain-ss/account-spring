package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
    @GetMapping("/jwt")
    public String Home(@RequestParam String param) {
        return "Hellow jwt";

    }
}
