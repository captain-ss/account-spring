package com.example.demo.controller;

import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
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
    public ResponseEntity<User> createUser() throws UserEmailAlreadyTakenException {
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, Calendar.NOVEMBER, 10);
            final Date date = calendar.getTime();
            User createdUser = new User("name","name.name", "username", "image_id", "phone", 20000, date, 200000L, "password");;
            userService.createUser(createdUser);
            return ResponseEntity.ok(createdUser);
        } catch (UserEmailAlreadyTakenException e){
            System.out.println(e.toString());
            throw e;
        } catch (Exception e){
            System.out.println(e.toString()+"Exception found");
            throw e;
        }
    }

    @GetMapping(path = "hi")
    public String hello(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.NOVEMBER, 10);
        final Date date = calendar.getTime();
        User user = new User("name","name.name", "username", "image_id", "phone", 20000, date, 200000L, "password");;
        User createdUser = this.userService.createUser(user);
        return createdUser.toString();
    }

}
