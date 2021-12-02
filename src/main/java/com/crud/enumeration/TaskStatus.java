package com.crud.enumeration;

public enum TaskStatus {
    PENDING("PENDING"),
    PROGRESS("PROGRESS"),
    HOLD("HOLD"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    public final String text;

    private TaskStatus(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.text;
    }
}
