package com.example.demo.service;

import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.exceptions.user.UserNotFoundException;
import com.example.demo.exceptions.user.UsernameAlreadyTakenException;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id) throws UserNotFoundException;
    User getUserByUsername(String username);
    User createUser(User user) throws UserEmailAlreadyTakenException, UsernameAlreadyTakenException;
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
