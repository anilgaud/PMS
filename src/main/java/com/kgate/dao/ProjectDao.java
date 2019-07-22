package com.kgate.dao;

import java.util.List;

import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;

import com.kgate.model.TaskDTO;

public interface ProjectDao {

    public void createProject(ProjectDetails project);

    List<ProjectDetails> dispalyProjects();

    public int getManagerid(String email);

    //return projectName for CEO
//          public List<String> getProjectName();
    List<ProjectDetails> getProjectByEmail(String email);

    //List project details and Status
    public List<TaskDTO> displayAllStatus(int id);

    //ListProject Report Details DTO
    public List<ProjectReportDTO> listProjectReport();
    //display project name using projectId

    public String displayProjectName(int id);

    //return projectdetails of employee using manager email
    public List<TaskDTO> displayAllStatus2(String email, int id);

    //return projectdetails of employee using manager email
    public List<TaskDTO> displayAllStatus3(String email, int id);
    
    public String getmanagernameformail(String email);

    
    public List<ProjectDetails> getProjectDates(int projectId);

   //return project name for duplicate check
    public String findproject(String projectname);
    
    public void deleteproject(int id);
    
   public ProjectDetails getProjectById(int id);

}
