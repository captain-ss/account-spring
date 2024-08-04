package com.example.demo.service;

import com.example.demo.exceptions.user.UserEmailAlreadyTakenException;
import com.example.demo.exceptions.user.UserNotFoundException;
import com.example.demo.exceptions.user.UsernameAlreadyTakenException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> queryUser = userRepository.findById(id);
        if(queryUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        return queryUser.get();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        System.out.println(username+" username");
        System.out.println(user.toString()+" usersdf");
        return user;
    }

    @Override
    public User createUser(User user) throws UserEmailAlreadyTakenException, UsernameAlreadyTakenException {
        try {
            System.out.println(user);
            final User userByEmail=this.userRepository.findByEmail(user.getEmail());
            if(userByEmail != null){
                throw new UserEmailAlreadyTakenException("User email is already taken");
            }
            final User userByUsername=this.userRepository.findByUsername(user.getUsername());
            if(userByUsername!=null){
                throw new UsernameAlreadyTakenException("Username is already taken");
            }
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
        } catch (UsernameAlreadyTakenException | UserEmailAlreadyTakenException e){
            throw e;
        }
        catch (Exception e) {
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
