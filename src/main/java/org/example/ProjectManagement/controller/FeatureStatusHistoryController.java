package org.example.ProjectManagement.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.enums.FeatureStatus;
import org.example.ProjectManagement.service.FeatureStatusHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/feature-status-update")
public class FeatureStatusHistoryController extends HttpServlet {
    private final FeatureStatusHistoryService featureStatusHistoryService = FeatureStatusHistoryService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(FeatureStatusHistoryController.class);
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String description, id=null;
        FeatureStatus status=null;
        FeatureStatus nextStatus=null;
        description = req.getParameter(Constants.DESCRIPTION);
        if (req.getParameter(Constants.ACCEPT) != null) {
            id = req.getParameter(Constants.ACCEPT);
            status = FeatureStatus.IN_REVIEW;
            nextStatus = status.next();
        } else if (req.getParameter(Constants.REJECT) != null) {
            id = req.getParameter(Constants.REJECT);
            status = FeatureStatus.PENDING;
            nextStatus = status.next();
        } else if(req.getParameter(Constants.CHANGE) != null) {
            id = req.getParameter(Constants.CHANGE);
            status = FeatureStatus.valueOf(req.getParameter(Constants.STATUS));
            nextStatus = status;
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            featureStatusHistoryService.save(id, description, nextStatus);
        } catch (Exception e) {
            logger.error("Error occurred while changing feature status: {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }
        resp.sendRedirect("/dashboard");
    }
}
