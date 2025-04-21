package ru.alexander.NauJava.entity;

import java.util.List;

public class ReportContent {
    private List<Application> applications;
    private Long appListTime;
    private Long numberOfUsers;
    private Long userCountTime;
    private Long generalTime;

    public ReportContent() {}

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setAppListTime(Long appListTime) {
        this.appListTime = appListTime;
    }

    public Long getAppListTime() {
        return appListTime;
    }

    public void setNumberOfUsers(Long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public Long getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setUserCountTime(Long userCountTime) {
        this.userCountTime = userCountTime;
    }

    public Long getUserCountTime() {
        return userCountTime;
    }

    public void setGeneralTime(Long generalTime) {
        this.generalTime = generalTime;
    }

    public Long getGeneralTime() {
        return generalTime;
    }
}
