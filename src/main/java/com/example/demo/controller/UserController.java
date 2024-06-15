package com.example.demo.controller;

import com.example.demo.exceptions.user.UserException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    @GetMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(UserException.class)
    public ResponseEntity<User> createUser() throws UserException {
        try{
            User createdUser = new User("name", "name.name");
            userService.createUser(createdUser);
            return ResponseEntity.ok(createdUser);
        } catch (UserException e){
            System.out.println(e.toString());
            throw e;
        } catch (Exception e){
            System.out.println(e.toString()+"Exception found");
            throw e;
        }
    }

    @GetMapping(path = "hi")
    public String hello(){
        return "Hello world";
    }

}
