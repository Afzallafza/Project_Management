package org.example.ProjectManagement.validator;

public class RoleValidator {
    public static void validate(String role)  {
        if(role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }
}
