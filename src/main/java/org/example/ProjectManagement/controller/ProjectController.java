package org.example.ProjectManagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.service.ProjectService;
import org.example.ProjectManagement.service.UserService;
import org.example.ProjectManagement.validator.ProjectValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/projects")
public class ProjectController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final ProjectService projectService = ProjectService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UserDto> leads= userService.findAllLeads();
            List<UserDto> managers = userService.findAllManagers();
            request.setAttribute(Constants.LEADS, leads);
            request.setAttribute(Constants.MANAGERS, managers);
            request.getRequestDispatcher("projectForm.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error occurred while fetching project form: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        try {
            ProjectValidator.validate(request);
            int managerId = Integer.parseInt(request.getParameter(Constants.MANAGER));
            int leadId = Integer.parseInt(request.getParameter(Constants.LEAD));
            String name = request.getParameter(Constants.NAME);
            projectService.save(name, managerId, leadId);
            session.invalidate();
            response.sendRedirect("/projects");
        }catch (IllegalArgumentException e){
            logger.error("Error occurred while providing input: {}", e.getMessage(), e);            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());        }
        catch (Exception e) {
            logger.error("Error occurred while fetching project form: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");

        }

    }
}
