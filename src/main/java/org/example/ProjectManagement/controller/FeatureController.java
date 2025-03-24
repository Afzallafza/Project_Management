package org.example.ProjectManagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ProjectManagement.Constants;
import org.example.ProjectManagement.dto.FeatureDto;
import org.example.ProjectManagement.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/projects/*")
public class FeatureController extends HttpServlet {
    private final FeatureService featureService = FeatureService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(FeatureController.class);
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String featureId = pathInfo.substring(1);
            try {
                List<FeatureDto> features = featureService.findAllByProjectId(Integer.parseInt(featureId));
                req.setAttribute(Constants.FEATURES, features);
            }catch (Exception e){
                logger.error("Error occurred while fetching projects: {}", e.getMessage(), e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
