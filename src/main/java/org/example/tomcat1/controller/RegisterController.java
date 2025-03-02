package org.example.tomcat1.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tomcat1.model.User;
import org.example.tomcat1.service.UserService;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterController extends HttpServlet {
    private UserService userService=UserService.getInstance();
    public void init(ServletConfig config) throws ServletException {
        System.out.println("RegisterController");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        try{
            userService.save(new User(name,username,email,password));
            response.sendRedirect("/login");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
