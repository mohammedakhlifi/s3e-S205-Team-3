package com.election.electionbackend.model;

public class BackendStatus {

    private String message;

    // Default constructor
    public BackendStatus() {
    }

    // Constructor with message parameter
    public BackendStatus(String message) {
        this.message = message;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Optional: toString() method for easy printing
    @Override
    public String toString() {
        return "BackendStatus{message='" + message + "'}";
    }
}
