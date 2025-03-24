package org.example.ProjectManagement.validator;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ProjectManagement.Constants;

import java.util.ArrayList;
import java.util.List;

public class FeatureValidator {

    public static void validate(HttpServletRequest request) {
        StringBuilder errors = new StringBuilder();
        boolean exception = false;
        if (request.getParameter(Constants.NAME) == null) {
            exception = true;
            errors.append(Constants.NAME + " parameter is required.");
        }
        if (request.getParameter(Constants.DEVELOPER) == null) {
            exception = true;
            errors.append(Constants.DEVELOPER + " parameter is required.");
        }
        if (request.getParameter(Constants.PRIORITY) == null) {
            exception = true;
            errors.append(Constants.PRIORITY + " parameter is required.");
        }
        if (request.getParameter(Constants.DESCRIPTION) == null) {
            exception = true;
            errors.append(Constants.DESCRIPTION + " parameter is required.");
        }
        if (exception) {
            throw new IllegalArgumentException(errors.toString());
        }
    }

    public static String validatePriorityOrder(String priorityOrder) {
        String order = "asc";
        if (priorityOrder != null) {
            order = priorityOrder;
        }
        return order;
    }

    public static List<String> validateStatusList(HttpServletRequest req) {
        if (req.getParameterValues(Constants.SELECTED_STATUS) == null) {
            return new ArrayList<>();
        } else {
            return List.of(req.getParameterValues(Constants.SELECTED_STATUS));
        }
    }
}
