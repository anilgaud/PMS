package com.kgate.model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "task_details")
public class TaskDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int task_id;

    @Column
    private String task_Type;

    @Column
    private String task_Name;
    @Column
    private String status;
    @Column
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    private Date tStart_Time;
    @Column
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Temporal(TemporalType.DATE)
    private Date tEnd_Time;
    @Column
    private String Emp_Email;
    @Column
    private String tSub_Date;
    @Column
    private int managerId;
    @Column
    private String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String gettSub_Date() {
        return tSub_Date;
    }

    public void settSub_Date(String tSub_Date) {
        this.tSub_Date = tSub_Date;
    }

    @Column
    private int projectId;

    @Column
    private String taskStatus;

    public TaskDetails() {
        super();
    }

    public TaskDetails(String task_Type, String task_Name, String status) {
        super();
        this.task_Type = task_Type;
        this.task_Name = task_Name;
        this.status = status;
    }

    public TaskDetails(String task_Type, String task_Name, String status, Date tStart_Time2, Date tEnd_Time2, String emp_Email2) {
        super();
        this.task_Type = task_Type;
        this.task_Name = task_Name;
        this.status = status;
        this.tStart_Time = tStart_Time;
        this.tEnd_Time = tEnd_Time;
        this.Emp_Email = Emp_Email;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date gettStart_Time() {
        return tStart_Time;
    }

    public void settStart_Time(Date tStart_Time) {
        this.tStart_Time = tStart_Time;
    }

    public Date gettEnd_Time() {
        return tEnd_Time;
    }

    public void settEnd_Time(Date tEnd_Time) {
        this.tEnd_Time = tEnd_Time;
    }

    public String getEmp_Email() {
        return Emp_Email;
    }

    public void setEmp_Email(String emp_Email) {
        this.Emp_Email = emp_Email;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "TaskDetails{" + "task_id=" + task_id + ", task_Type=" + task_Type + ", task_Name=" + task_Name + ", status=" + status + ", tStart_Time=" + tStart_Time + ", tEnd_Time=" + tEnd_Time + ", Emp_Email=" + Emp_Email + ", tSub_Date=" + tSub_Date + ", managerId=" + managerId + ", emp_name=" + emp_name + ", projectId=" + projectId + ", taskStatus=" + taskStatus + '}';
    }

}
