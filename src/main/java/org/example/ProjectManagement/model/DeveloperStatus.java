package org.example.ProjectManagement.model;

public class DeveloperStatus {
    private User developer;
    private int countAssignedFeature;
    private int countPendingFeature;
    private int countOngoingFeature;
    private int countInReviewFeature;

    public DeveloperStatus(Builder builder) {
        this.developer = builder.developer;
        this.countPendingFeature = builder.countPendingFeature;
        this.countAssignedFeature = builder.countAssignedFeature;
        this.countOngoingFeature = builder.countOngoingFeature;
        this.countInReviewFeature = builder.countInReviewFeature;
    }
    public static class Builder {
        private User developer;
        private int countAssignedFeature;
        private int countPendingFeature;
        private int countOngoingFeature;
        private int countInReviewFeature;
        public Builder developer(User developer) {
            this.developer = developer;
            return this;
        }
        public Builder countAssignedFeature(int countAssignedFeature) {
            this.countAssignedFeature = countAssignedFeature;
            return this;
        }
        public Builder countPendingFeature(int countPendingFeature) {
            this.countPendingFeature = countPendingFeature;
            return this;
        }
        public Builder countOngoingFeature(int countOngoingFeature) {
            this.countOngoingFeature = countOngoingFeature;
            return this;
        }
        public Builder countInReviewFeature(int countInReviewFeature) {
            this.countInReviewFeature = countInReviewFeature;
            return this;
        }
        public DeveloperStatus build() {
            return new DeveloperStatus(this);
        }
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public int getCountAssignedFeature() {
        return countAssignedFeature;
    }

    public void setCountAssignedFeature(int countAssignedFeature) {
        this.countAssignedFeature = countAssignedFeature;
    }

    public int getCountPendingFeature() {
        return countPendingFeature;
    }

    public void setCountPendingFeature(int countPendingFeature) {
        this.countPendingFeature = countPendingFeature;
    }

    public int getCountOngoingFeature() {
        return countOngoingFeature;
    }

    public void setCountOngoingFeature(int countOngoingFeature) {
        this.countOngoingFeature = countOngoingFeature;
    }

    public int getCountInReviewFeature() {
        return countInReviewFeature;
    }

    public void setCountInReviewFeature(int countInReviewFeature) {
        this.countInReviewFeature = countInReviewFeature;
    }
}
