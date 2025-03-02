package org.example.tomcat1.model;

import org.example.tomcat1.Enum.Priority;
import org.example.tomcat1.Enum.Status;

import java.time.LocalDateTime;

public class Feature {
    private int id;
    private String name;
    private Project project;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User developer;
    private Priority priority;
    private Status status;
    public Feature(Priority priority, String name, LocalDateTime startDate, Project project, User developer, Status status) {
        this.priority = priority;
        this.name = name;
        this.startDate = startDate;
        this.project = project;
        this.developer = developer;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", project=" + project +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", developer=" + developer +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
