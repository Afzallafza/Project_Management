package org.example.tomcat1.model;

import java.security.PrivateKey;
import java.time.LocalDateTime;

public class Project {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User projectManager;
    private User teamLead;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectManager=" + projectManager +
                ", teamLead=" + teamLead +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public User getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(User teamLead) {
        this.teamLead = teamLead;
    }

    public Project(String name, LocalDateTime startDate, LocalDateTime endDate, User projectManager, User teamLead) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManager = projectManager;
        this.teamLead = teamLead;
    }
}
