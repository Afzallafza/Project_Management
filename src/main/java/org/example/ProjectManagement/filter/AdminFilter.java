package org.example.ProjectManagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/add-role","/projects"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ys");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username.equals("admin")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            request.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
        }

    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
