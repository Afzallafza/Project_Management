package org.example.ProjectManagement.dto;

import org.example.ProjectManagement.model.User;

public class ProjectDto {
    private int id;
    private String name;
    private User projectManager;
    private User teamLead;

    public ProjectDto(int id, String name, User projectManager, User teamLead) {
        this.id = id;
        this.name = name;
        this.projectManager = projectManager;
        this.teamLead = teamLead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectManager=" + projectManager +
                ", teamLead=" + teamLead +
                '}';
    }
}
