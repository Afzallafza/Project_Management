package org.example.ProjectManagement.dto;

import org.example.ProjectManagement.enums.FeaturePriority;
import org.example.ProjectManagement.enums.FeatureStatus;
import org.example.ProjectManagement.model.Project;
import org.example.ProjectManagement.model.User;

public class FeatureDto {
    private int id;
    private String name;
    private Project project;
    private User developer;
    private FeaturePriority priority;
    private FeatureStatus status;
    private String description;

    public FeatureDto(int id, String name, Project project, User developer, FeaturePriority priority, FeatureStatus status, String description) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.developer = developer;
        this.priority = priority;
        this.status = status;
        this.description = description;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public FeaturePriority getPriority() {
        return priority;
    }

    public void setPriority(FeaturePriority priority) {
        this.priority = priority;
    }

    public FeatureStatus getStatus() {
        return status;
    }

    public void setStatus(FeatureStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FeatureDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", developer=" + developer +
                ", priority=" + priority +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
