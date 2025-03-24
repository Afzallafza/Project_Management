package org.example.ProjectManagement.validator;

import jakarta.servlet.http.HttpServletRequest;

public class UserValidator {
    public static String validate(HttpServletRequest req) {
        boolean exception = false;
        StringBuilder errors = new StringBuilder();
        if (req.getParameter("name") == null || req.getParameter("name").isEmpty()) {
            exception = true;
            errors.append("Name is required.\n");
        }
        if (req.getParameter("username") == null || req.getParameter("username").isEmpty()) {
            exception = true;
            errors.append("Username is required.\n");
        }
        if (req.getParameter("email") == null || req.getParameter("email").isEmpty()) {
            exception = true;
            errors.append("Email is required.\n");
        }
        if (req.getParameter("password") == null || req.getParameter("password").isEmpty()) {
            exception = true;
            errors.append("Password is required.\n");
        }
        return errors.toString();
    }
}
