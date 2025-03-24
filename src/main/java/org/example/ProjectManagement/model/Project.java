package org.example.ProjectManagement.model;

import java.time.LocalDateTime;

public class Project {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User projectManager;
    private User teamLead;

    public Project(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.projectManager = builder.projectManager;
        this.endDate = builder.endDate;
        this.teamLead = builder.teamLead;
    }

    public static class Builder {
        private int id;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private User projectManager;
        private User teamLead;
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder startDate() {
            this.startDate = LocalDateTime.now();
            return this;
        }
        public Builder startDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }
        public Builder projectManager(User projectManager) {
            this.projectManager = projectManager;
            return this;
        }
        public Builder teamLead(User teamLead) {
            this.teamLead = teamLead;
            return this;
        }
        public Project build() {
            return new Project(this);
        }
    }

    public User getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(User teamLead) {
        this.teamLead = teamLead;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectManager=" + projectManager +
                ", teamLead=" + teamLead +
                '}';
    }
}
