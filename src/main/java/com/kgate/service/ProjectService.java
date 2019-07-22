package com.kgate.service;

import java.util.List;

import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;
import com.kgate.model.TaskDTO;

public interface ProjectService {

    public void createProject(ProjectDetails project);

    List<ProjectDetails> dispalyProjects();

    public int getManagerid(String email);

    List<ProjectDetails> getProjectByEmail(String email);

    //list of ProjectStatus
    public List<TaskDTO> displayAllStatus(int id);

    //List ProjectReport Details
    public List<ProjectReportDTO> listProjectReport();

//return project name using projectId
    public String displayProjectName(int id);

    //return projectdetails of employee using manager email
    public List<TaskDTO> displayAllStatus2(String email,int id);
    
    //return projectdetails of employee using manager email
    public List<TaskDTO> displayAllStatus3(String email, int id);
    
    public String getmanagernameformail(String email);

    public List<ProjectDetails> getProjectDates(int projectId);

    public String findproject(String projectname);
    
    public void deleteproject(int id);
    
    public ProjectDetails getProjectById(int id);

}
