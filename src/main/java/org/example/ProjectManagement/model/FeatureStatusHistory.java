package org.example.ProjectManagement.model;

import org.example.ProjectManagement.enums.FeatureStatus;

import java.time.LocalDateTime;

public class FeatureStatusHistory {
    private int id;
    private Feature feature;
    private String description;
    private LocalDateTime timeOfChange;
    private FeatureStatus status;

    public FeatureStatusHistory(Builder builder) {
        this.id = builder.id;
        this.feature = builder.feature;
        this.description = builder.description;
        this.timeOfChange = builder.timeOfChange;
        this.status = builder.status;
    }

    public static class Builder {
        private int id;
        private Feature feature;
        private String description;
        private LocalDateTime timeOfChange;
        private FeatureStatus status;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder feature(Feature feature) {
            this.feature = feature;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timeOfChange(LocalDateTime timeOfChange) {
            this.timeOfChange = timeOfChange;
            return this;
        }

        public Builder status(FeatureStatus status) {
            this.status = status;
            return this;
        }

        public FeatureStatusHistory build() {
            return new FeatureStatusHistory(this);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeatureStatus getStatus() {
        return status;
    }

    public void setStatus(FeatureStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimeOfChange() {
        return timeOfChange;
    }

    public void setTimeOfChange(LocalDateTime timeOfChange) {
        this.timeOfChange = timeOfChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "FeatureStatusHistory{" +
                "id=" + id +
                ", feature=" + feature +
                ", description='" + description + '\'' +
                ", timeOfChange=" + timeOfChange +
                ", status=" + status +
                '}';
    }
}
