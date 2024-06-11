package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "create")
    public ResponseEntity<User> createUser() {
        User createdUser = new User("name", "name.name");
        userService.createUser(createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(path = "hi")
    public String hello(){
        return "Hello world";
    }

}
