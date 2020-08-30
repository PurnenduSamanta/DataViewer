package com.example.dataviewer;

public class ModelForPost
{
    private String status,message;

    @Override
    public String toString() {
        return "ModelForPost{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelForPost(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
