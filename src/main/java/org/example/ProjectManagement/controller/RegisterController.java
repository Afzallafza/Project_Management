package org.example.ProjectManagement.controller;

import com.mysql.cj.protocol.x.Notice;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.model.User;
import org.example.ProjectManagement.service.UserService;
import org.example.ProjectManagement.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter(Constants.NAME);
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String username = request.getParameter(Constants.USERNAME);
        try {
            String inputError=UserValidator.validate(request);
            if(inputError.isEmpty()){
                User user = new User.Builder()
                        .name(name)
                        .username(username)
                        .email(email)
                        .password(password)
                        .build();
                userService.save(user);
                response.sendRedirect("/login");
            }else{
                request.getSession().setAttribute(Constants.INPUT_ERROR,inputError);
                response.sendRedirect("/register");
            }
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
            request.setAttribute(Constants.USER_ERROR, e.getMessage());
            response.sendRedirect("/register");
        } catch (Exception e) {
            logger.error("Error occurred while fetching registration form: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("RegisterForm.jsp").forward(request, response);
    }
}
