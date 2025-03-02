package org.example.tomcat1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.tomcat1.exception.UserNotFoundException;
import org.example.tomcat1.model.User;
import org.example.tomcat1.service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            User user = userService.findByUsername(username);
            if (password.equals(user.getPassword())) {
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                response.sendRedirect("/");
            } else {

                session.setAttribute("passError", "Wrong password");
                response.sendRedirect("/login");
            }
        } catch (UserNotFoundException e) {
            session.setAttribute("userError", e.getMessage());
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
