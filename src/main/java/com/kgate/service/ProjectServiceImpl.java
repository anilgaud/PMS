package com.kgate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.dao.ProjectDao;
import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;
import com.kgate.model.TaskDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    @Transactional
    public void createProject(ProjectDetails project) {
        projectDao.createProject(project);

    }

    @Override
    @Transactional
    public List<ProjectDetails> dispalyProjects() {
        return projectDao.dispalyProjects();
    }

    @Override
    @Transactional
    public int getManagerid(String email) {
        return projectDao.getManagerid(email);
    }

    @Override
    @Transactional
    public List<TaskDTO> displayAllStatus(int id) {
        return projectDao.displayAllStatus(id);
    }

    @Override
    @Transactional
    public List<ProjectDetails> getProjectByEmail(String email) {

        return projectDao.getProjectByEmail(email);
    }

    @Override
    @Transactional
    public List<ProjectReportDTO> listProjectReport() {
        return projectDao.listProjectReport();
    }

    @Override
    @Transactional
    public String displayProjectName(int id) {
        return projectDao.displayProjectName(id);
    }

    @Override
    @Transactional
    public List<TaskDTO> displayAllStatus2(String email, int id) {
        return projectDao.displayAllStatus2(email, id);
    }

    @Override
    @Transactional
    public List<TaskDTO> displayAllStatus3(String email, int id) {
        return projectDao.displayAllStatus3(email, id);
    }

	@Override
	 @Transactional
	public String getmanagernameformail(String email) {
		
		return projectDao.getmanagernameformail(email);
	}

	@Override
	 @Transactional

	public List<ProjectDetails> getProjectDates(int projectId) {
		return projectDao.getProjectDates(projectId);
	}
	@Override
	 @Transactional
	public String findproject(String projectname) {
		
		return projectDao.findproject(projectname);
	}

	@Override
	 @Transactional
	public void deleteproject(int id) {
		
		projectDao.deleteproject(id);
	}

	@Override
	 @Transactional
	public ProjectDetails getProjectById(int id) {
	
		return projectDao.getProjectById(id);
	}
	

}
