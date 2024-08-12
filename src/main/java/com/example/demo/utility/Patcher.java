package com.example.demo.utility;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Patcher {
    public static void userPatcher(User existingUser, User patchUser) throws IllegalAccessException {
        Class<User> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();
        for(Field field: fields){
            System.out.println(field.toString());
            field.setAccessible(true);
            Object value = field.get(patchUser);
            if(value != null){
              field.set(existingUser, value);
            }
            field.setAccessible(false);
        }
    }
}
