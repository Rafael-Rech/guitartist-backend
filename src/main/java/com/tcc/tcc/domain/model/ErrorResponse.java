package com.tcc.tcc.domain.model;

public class ErrorResponse {
    private String dateTime;
    private Integer status;
    private String title;
    private String message;
    
    public ErrorResponse(String dateTime, Integer status, String title, String message) {
        this.dateTime = dateTime;
        this.status = status;
        this.title = title;
        this.message = message;
    }
    
    public String getDateTime() {
        return dateTime;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
