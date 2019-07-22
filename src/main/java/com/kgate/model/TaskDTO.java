package com.kgate.model;

public class TaskDTO {

    private String project_Name;
    private String task_Type;
    private String task_Name;
    private String name;
    private String tStartDate;
    private String tEndDate;
    private String status;
    private int id;
    private String email;
    private String emp_name;
    private String tsubDate;
    private String estimateDays;
    private String actualDays;
    private String delayDays;

    public String getEstimateDays() {
        return estimateDays;
    }

    public void setEstimateDays(String estimateDays) {
        this.estimateDays = estimateDays;
    }

    public String getActualDays() {
        return actualDays;
    }

    public void setActualDays(String actualDays) {
        this.actualDays = actualDays;
    }

    public String getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(String delayDays) {
        this.delayDays = delayDays;
    }

    public String getTsubDate() {
        return tsubDate;
    }

    public void setTsubDate(String tsubDate) {
        this.tsubDate = tsubDate;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String gettStartDate() {
        return tStartDate;
    }

    public void settStartDate(String tStartDate) {
        this.tStartDate = tStartDate;
    }

    public String gettEndDate() {
        return tEndDate;
    }

    public void settEndDate(String tEndDate) {
        this.tEndDate = tEndDate;
    }

    public String getProject_Name() {
        return project_Name;
    }

    public void setProject_Name(String project_Name) {
        this.project_Name = project_Name;
    }

    public String getTask_Type() {
        return task_Type;
    }

    public void setTask_Type(String task_Type) {
        this.task_Type = task_Type;
    }

    public String getTask_Name() {
        return task_Name;
    }

    public void setTask_Name(String task_Name) {
        this.task_Name = task_Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
