package org.example.ProjectManagement.model;

import org.example.ProjectManagement.enums.FeaturePriority;
import org.example.ProjectManagement.enums.FeatureStatus;

import java.time.LocalDateTime;

public class Feature {
    private int id;
    private String name;
    private Project project;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User developer;
    private FeaturePriority priority;
    private FeatureStatus status;
    private User qa;
    private String description;

    private Feature(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.project = builder.project;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.developer = builder.developer;
        this.priority = builder.priority;
        this.status = builder.status;
        this.qa = builder.qa;
        this.description = builder.description;
    }

    public static class Builder {
        private int id;
        private String name;
        private Project project;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private User developer;
        private FeaturePriority priority;
        private FeatureStatus status;
        private User qa;
        private String description;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder project(Project project) {
            this.project = project;
            return this;
        }

        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder endDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder developer(User developer) {
            this.developer = developer;
            return this;
        }

        public Builder priority(FeaturePriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder status(FeatureStatus status) {
            this.status = status;
            return this;
        }

        public Builder qa(User qa) {
            this.qa = qa;
            return this;
        }

        public Feature build() {
            return new Feature(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getQa() {
        return qa;
    }

    public void setQa(User qa) {
        this.qa = qa;
    }

    public FeatureStatus getStatus() {
        return status;
    }

    public void setStatus(FeatureStatus status) {
        this.status = status;
    }

    public FeaturePriority getPriority() {
        return priority;
    }

    public void setPriority(FeaturePriority priority) {
        this.priority = priority;
    }

    public User getDeveloper() {
        return developer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", developer=" + developer +
                ", priority=" + priority +
                ", status=" + status +
                ", qa=" + qa +
                ", description='" + description + '\'' +
                '}';
    }
}
