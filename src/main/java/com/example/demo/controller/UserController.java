package com.example.demo.controller;

import com.example.demo.configuration.UserAuthenticationProvider;
import com.example.demo.exceptions.user.CredentialMismatchException;
import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.exceptions.user.UserNotFoundException;
import com.example.demo.exceptions.user.UsernameAlreadyTakenException;
import com.example.demo.model.User;
import com.example.demo.payload.CreateUserPayload;
import com.example.demo.payload.LoginUserPayload;
import com.example.demo.service.UserService;
import com.example.demo.utility.Patcher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    // Get all users
    @GetMapping(path = {"/", ""})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(path = {"/", ""})
    public ResponseEntity<List<User>> getAllUsersPost() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable Long userId){
        System.out.println("User ID "+userId.toString());
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Principal "+username);
        try{
            User user = this.userService.getUserById(userId);
            if(!user.getUsername().equals(username)){
                throw new UserNotFoundException("User not found");
            }
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e){
            System.out.println("User not found "+ userId);
            throw e;
        } catch (Exception e){
            throw e;
        }
    };

    @ResponseBody
    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserPayload userPayload) throws UserEmailAlreadyTakenException {
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, Calendar.NOVEMBER, 10);
            final Date date = calendar.getTime();
            User createdUser = new User(
                userPayload.name(),
                userPayload.email(), userPayload.username(),
                "image_id", userPayload.phone(),
                userPayload.monthly_salary(), date, userPayload.account_balance() != null ? userPayload.account_balance() : Long.valueOf(0L), userPayload.password());;
            User newUser = userService.createUser(createdUser);
            String jwtToken = userAuthenticationProvider.createJwtToken(newUser.getUsername(), newUser.getHashed_password());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.AUTHORIZATION, jwtToken);
            return new ResponseEntity<User>(newUser, httpHeaders, HttpStatus.CREATED);
        } catch (UserEmailAlreadyTakenException | UsernameAlreadyTakenException e){
            System.out.println(e.toString());
            throw e;
        } catch (Exception e){
            System.out.println(e.toString()+"Exception found");
            throw e;
        }
    }

    @ResponseBody
    @PatchMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> patchUser(@RequestBody User patchUser) throws UserNotFoundException{
        try {
            final String patchUserUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            User existingUser = this.userService.getUserByUsername(patchUserUsername);
            System.out.println(existingUser);
            Patcher.userPatcher(existingUser, patchUser);
            this.userService.saveUser(existingUser);
            return new ResponseEntity<User>(existingUser, HttpStatus.ACCEPTED);
        } catch (UserNotFoundException e){
            throw e;
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }
    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> loginUser(@Valid @RequestBody LoginUserPayload loginUserPayload) throws UserNotFoundException {
        try {
            User user = userService.getUserByUsername(loginUserPayload.username());
            if(!user.getHashed_password().equals(loginUserPayload.password())){
                throw new CredentialMismatchException("Username or password mismatch");
            }
            String jwtToken = userAuthenticationProvider.createJwtToken(user.getUsername(), user.getHashed_password());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.AUTHORIZATION, jwtToken);
            return new ResponseEntity<User>(user, httpHeaders, HttpStatus.ACCEPTED);
        } catch (UserNotFoundException e){
            System.out.println("UserNotFoundException "+e.getClass().getName());
            throw e;
        } catch (CredentialMismatchException e){
            throw e;
        }
    }

    @GetMapping(path = "hi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@Valid @RequestBody CreateUserPayload user){
        System.out.println(user.toString());
        return "Hello";
    }

}
