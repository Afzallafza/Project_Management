package org.example.ProjectManagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.exception.UserNotFoundException;
import org.example.ProjectManagement.service.UserService;
import org.example.ProjectManagement.validator.InputValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(Constants.USERNAME);
        String password = request.getParameter(Constants.PASSWORD);
        HttpSession session = request.getSession();
        try {
            InputValidator.isNULL(username);
            InputValidator.isNULL(password);
            UserDto userdto = userService.findByUsername(username);
            if (userService.validateCredentials(userdto,password)) {
                session.setAttribute(Constants.USERNAME, username);
                session.setAttribute(Constants.PASSWORD, password);
                session.setAttribute(Constants.ROLE, userdto.getRole());
                response.sendRedirect("/");
            } else {
                session.setAttribute(Constants.PASS_ERROR, "Wrong password");
                response.sendRedirect("/login");
            }
        }catch (UserNotFoundException e){
            session.setAttribute(Constants.USER_ERROR, e.getMessage());
            response.sendRedirect("/login");
        }catch (IllegalArgumentException e) {
            session.setAttribute(Constants.USER_ERROR, e.getMessage());
            response.sendRedirect("/login");
        } catch (Exception e) {
            logger.error("Error occurred while fetching login form: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
