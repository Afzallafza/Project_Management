package org.example.ProjectManagement.validator;

import org.example.ProjectManagement.service.UserService;

public class InputValidator {
    private static final UserService userService= UserService.getInstance();
    public static void isNULL(String username) throws IllegalArgumentException{
        if(username==null || username.isEmpty()){
            throw new IllegalArgumentException("Username can not be blank or empty");
        }


    }
}
