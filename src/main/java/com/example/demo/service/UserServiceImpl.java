package com.example.demo.service;

import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.exceptions.user.UsernameAlreadyTakenException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<User>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) throws UserEmailAlreadyTakenException {
        try {
            System.out.println(user);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.toString() + "Exception string");
            if(e.getCause() instanceof ConstraintViolationException constraintViolationException){
                final String constraintName = constraintViolationException.getConstraintName();
                System.out.println(constraintViolationException.getSQLException().toString()+"get sql exception");
                System.out.println("Constraint name" + constraintName);
                if(constraintName != null && constraintName.contains("EMAIL NULLS FIRST")){
                    throw new UserEmailAlreadyTakenException("User email already taken");
                }
                if(constraintName != null && constraintName.contains("USERNAME NULLS FIRST")){
                    throw new UsernameAlreadyTakenException("Username already taken");
                }
            }
            System.out.println(((ConstraintViolationException)e.getCause()).getConstraintName()+"Exception cause");
            System.out.println(e.getMostSpecificCause().getMessage()+"Exception message");
            throw new UserEmailAlreadyTakenException("User email already taken");
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
