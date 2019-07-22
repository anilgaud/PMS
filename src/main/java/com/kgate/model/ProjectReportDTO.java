package com.kgate.model;

public class ProjectReportDTO {

    private String project_name;
    private String pStartDate;
    private String pEndDate;
    private String expectDate;
    private String CompleteTime;
    private String variationTime;
    private String proStatus;

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public String getVariationTime() {
        return variationTime;
    }

    public void setVariationTime(String variationTime) {
        this.variationTime = variationTime;
    }

    public String getCompleteTime() {
        return CompleteTime;
    }

    public void setCompleteTime(String CompleteTime) {
        this.CompleteTime = CompleteTime;
    }

    public String getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(String expectDate) {
        this.expectDate = expectDate;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getpStartDate() {
        return pStartDate;
    }

    public void setpStartDate(String pStartDate) {
        this.pStartDate = pStartDate;
    }

    public String getpEndDate() {
        return pEndDate;
    }

    public void setpEndDate(String pEndDate) {
        this.pEndDate = pEndDate;
    }
}
