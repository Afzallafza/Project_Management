package org.example.ProjectManagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.dto.FeatureDto;
import org.example.ProjectManagement.dto.ProjectDto;
import org.example.ProjectManagement.dto.UserDto;
import org.example.ProjectManagement.exception.ProjectNotFoundException;
import org.example.ProjectManagement.model.FeatureStatusHistory;
import org.example.ProjectManagement.service.FeatureService;
import org.example.ProjectManagement.service.FeatureStatusHistoryService;
import org.example.ProjectManagement.service.ProjectService;
import org.example.ProjectManagement.service.UserService;
import org.example.ProjectManagement.validator.FeatureValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    private final FeatureService featureService = FeatureService.getInstance();
    private final ProjectService projectService = ProjectService.getInstance();
    private final FeatureStatusHistoryService featureStatusHistoryService = FeatureStatusHistoryService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        switch (role) {
            case Constants.ROLE_DEVELOPER -> {
                try {
                    fetchDeveloperDashboardDetails(req, resp);
                    req.getRequestDispatcher("/developerDashboard.jsp").forward(req, resp);
                } catch (Exception e) {
                    logger.error("Error occurred while fetching features: {}", e.getMessage(), e);
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
                }
            }
            case Constants.ROLE_LEAD -> {
                try {
                    addLeadDashboardDetails(req);
                    req.getRequestDispatcher("/leadDashboard.jsp").forward(req, resp);
                } catch (Exception e) {
                    logger.error("Error occurred while loading the dashboard : {}.", e.getMessage(), e);
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
                }
            }
            case Constants.ROLE_MANAGER -> {
                try {
                    addManagerDashboardDetails(req);
                    req.getRequestDispatcher("/managerDashboard.jsp").forward(req, resp);
                } catch (Exception e) {
                    logger.error("Error occurred while fetching features : {}", e.getMessage(), e);
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
                }
            }
            default -> resp.sendRedirect("/add-role");
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role.equals(Constants.ROLE_DEVELOPER)) {
            developerDashboard(req, resp);
        } else if (role.equals(Constants.ROLE_LEAD)) {
            leadDashboard(req, resp);
        } else if (role.equals(Constants.ROLE_MANAGER)) {
            managerDashboard(req, resp);
        }
    }

    public void developerDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            String featureId =req.getParameter(Constants.SUBMITTED_FEATURE_ID);
            String priority = FeatureValidator.validatePriorityOrder(req.getParameter(Constants.PRIORITY_ORDER));
            List<String> statuses = FeatureValidator.validateStatusList(req);
            session.setAttribute(Constants.SELECTED_STATUS, statuses);
            session.setAttribute(Constants.SUBMITTED_FEATURE_ID, featureId);
            session.setAttribute(Constants.PRIORITY_ORDER, priority);
            resp.sendRedirect("/dashboard");
        } catch (IllegalArgumentException e) {
            logger.error("Error occurred while providing input: {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while fetching feature status form: {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void leadDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FeatureValidator.validate(req);
            String name = req.getParameter(Constants.NAME);
            int developer = Integer.parseInt(req.getParameter(Constants.DEVELOPER));
            String priority = req.getParameter(Constants.PRIORITY);
            String username = req.getSession().getAttribute(Constants.USERNAME).toString();
            String description = req.getParameter(Constants.DESCRIPTION);
            featureService.save(name, developer, priority, username, description);
            resp.sendRedirect("/dashboard");
        } catch (IllegalArgumentException e) {
            logger.error("Error occurred while providing input: {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Error occurred while fetching features : {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }

    }

    public void managerDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String submitted = req.getParameter(Constants.SUBMITTED);
        session.setAttribute(Constants.SUBMITTED, submitted);
        resp.sendRedirect("/dashboard");
    }

    public void fetchDeveloperDashboardDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException,ProjectNotFoundException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = req.getSession();
        String username = session.getAttribute(Constants.USERNAME).toString();
        List<FeatureStatusHistory> featureStatusHistories;
        String priority = "asc";
        if (session.getAttribute(Constants.PRIORITY_ORDER) != null) {
            priority = (String) session.getAttribute(Constants.PRIORITY_ORDER);
        }
        List<String> statuses = new ArrayList<>();
        if (session.getAttribute(Constants.SELECTED_STATUS) != null) {
            statuses = (List<String>) session.getAttribute(Constants.SELECTED_STATUS);
        }
        List<FeatureDto> features = featureService.findAllByDeveloperId(username, priority, statuses);
        req.setAttribute(Constants.FEATURES, features);
        if (session.getAttribute(Constants.SUBMITTED_FEATURE_ID) != null) {
            String featureId = session.getAttribute(Constants.SUBMITTED_FEATURE_ID).toString();
            FeatureDto feature = featureService.findById(featureId);
            featureStatusHistories = featureStatusHistoryService.findByFeatureId(feature.getId());
            req.setAttribute(Constants.SUBMITTED_FEATURE_ID, feature.getId());
            req.setAttribute(Constants.FEATURE, feature);
            req.setAttribute(Constants.FEATURE_STATUS_HISTORIES, featureStatusHistories);
        }
    }

    public void addLeadDashboardDetails(HttpServletRequest req) throws SQLException, IOException, ClassNotFoundException, ProjectNotFoundException {
        String username = req.getSession().getAttribute(Constants.USERNAME).toString();
        List<UserDto> developers = userService.findAllDevelopers();
        List<FeatureDto> features = featureService.findAllByLeadId(username);
        req.setAttribute(Constants.USERS, developers);
        req.setAttribute(Constants.FEATURES, features);
    }

    public void addManagerDashboardDetails(HttpServletRequest req) throws ProjectNotFoundException, SQLException, IOException, ClassNotFoundException {
        HttpSession session = req.getSession();
        String username = session.getAttribute(Constants.USERNAME).toString();
        List<ProjectDto> projects = projectService.findAllByManagerUsername(username);
        req.setAttribute(Constants.PROJECTS, projects);
        List<FeatureDto> features = new ArrayList<>();
        if (session.getAttribute(Constants.SUBMITTED) != null) {
            int submitted = Integer.parseInt(session.getAttribute(Constants.SUBMITTED).toString());
            req.setAttribute(Constants.SUBMITTED, submitted);
            features = featureService.findAllByProjectId(submitted);
        }
        req.setAttribute(Constants.FEATURES, features);
    }
}
