package org.example.ProjectManagement.validator;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ProjectManagement.Constants;

public class ProjectValidator {

    public static void validate(HttpServletRequest req) {
        boolean exception = false;
        StringBuilder errors = new StringBuilder();
        if (req.getParameter(Constants.MANAGER) == null || req.getParameter(Constants.MANAGER).isEmpty()) {
            errors.append("Missing required parameter 'manager'\n");
            exception = true;
        }
        if (req.getParameter(Constants.LEAD) == null || req.getParameter(Constants.LEAD).isEmpty()) {
            exception = true;
            errors.append("Missing required parameter 'lead'\n");
        }
        if (req.getParameter(Constants.NAME) == null || req.getParameter(Constants.NAME).isEmpty()) {
            exception = true;
            errors.append("Missing required parameter 'name'\n");
        }
        if (exception) {
            throw new IllegalArgumentException(errors.toString());
        }
    }
}
