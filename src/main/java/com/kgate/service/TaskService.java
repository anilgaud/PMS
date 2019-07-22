package com.kgate.service;

import com.kgate.model.TaskDTO;

import java.util.List;

import com.kgate.model.ProjectDetails;
import com.kgate.model.TaskDetails;

public interface TaskService {

    public List<TaskDetails> getalltaskdetails(String email);

    public void updateTask(TaskDetails taskDetails);

    public void updatetask1(String date, String email, int tid, String st);

    public TaskDetails getEmployeeTask(int taskid);

    public void addTask(TaskDetails task);

    public void deleteTask(int task_id);

    public List<TaskDetails> getAllTask();

    public List<TaskDetails> getByProjectId(int id);

    // return employee name from manager email
    public List<String> getEmpNameList(String email);

    // return task
    public TaskDetails getTask(int task_id);

    public List<TaskDetails> getTaskList(int mgrId);

    public List<TaskDTO> getEmpTasklist(String email);

    // return manageremail id
    public String getManagerEmail(String email);

    // return get employee name 
    public String getEmployeeName(String name);

    //return project name using task id
    public String getProjectName(int id);

//  return managereName id
    public String getManagerName(String email);

    //return employee email from emp name
    public String EmployeeEmail(String name);
}
