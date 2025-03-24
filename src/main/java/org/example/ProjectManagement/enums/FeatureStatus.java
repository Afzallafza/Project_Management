package org.example.ProjectManagement.enums;

public enum FeatureStatus {
    PENDING, ONGOING, IN_REVIEW, FINISHED;

    public FeatureStatus next() {
        return values()[this.ordinal() + 1];
    }
    public int getOrdinal() {
        return this.ordinal();
    }
}
