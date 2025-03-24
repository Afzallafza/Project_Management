package org.example.ProjectManagement.enums;

public enum FeaturePriority {
    TRIVIAL,MEDIUM,HIGH;
    public FeaturePriority next(){
        return values()[this.ordinal()+1];
    }
}
