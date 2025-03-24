package org.example.ProjectManagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
            filterChain.doFilter(request, response);
        } else if (session.getAttribute("username") == null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
