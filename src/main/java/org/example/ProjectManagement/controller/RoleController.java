package org.example.ProjectManagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.service.UserService;
import org.example.ProjectManagement.validator.RoleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/add-role")
public class RoleController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<UserDto> users = userService.findAll();
            req.setAttribute(Constants.USERS, users);
        } catch (Exception e) {
            logger.error("An exception has been caught.", e);
        }
        req.getRequestDispatcher("roleAssign.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String request = req.getParameter(Constants.ID);
        if (request != null) {
            try {
                RoleValidator.validate(req.getParameter(Constants.ASSIGN));
                String roleName = req.getParameter(Constants.ASSIGN);
                userService.updateRole(Integer.parseInt(request), roleName);
                resp.sendRedirect("/add-role");
            } catch (Exception e) {
                logger.error("An exception has been caught.", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            String id = req.getParameter("sth");
            try {
                userService.updateRole(Integer.parseInt(id), null);
                resp.sendRedirect("/add-role");
            }catch (IllegalArgumentException e){
                logger.error("Error occurred while providing input: {}", e.getMessage(), e);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "An error occurred while processing your request");
            }
            catch (Exception e) {
                logger.error("Error occurred while assigning role: {}", e.getMessage(), e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
            }
        }
    }
}
