package com.kgate.service;

import java.util.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.kgate.dao.TaskDao;
import com.kgate.model.ProjectDetails;
import com.kgate.model.TaskDTO;
import com.kgate.model.TaskDetails;

@Service
public class TaskSeviceImpl implements TaskService {

    @Autowired
    private TaskDao taskdao;

    @Override
    @Transactional
    public List<TaskDetails> getalltaskdetails(String email) {
        return taskdao.getalltaskdetails(email);
    }

    @Override
    @Transactional
    public void updateTask(TaskDetails taskDetails) {
        taskdao.updateTask(taskDetails);

    }

    @Override
    @Transactional
    public TaskDetails getEmployeeTask(int taskid) {
        return taskdao.getEmployeeTask(taskid);
    }

    @Override
    @Transactional
    public void addTask(TaskDetails task) {
        taskdao.addTask(task);

    }

    @Override
    @Transactional
    public List<TaskDetails> getByProjectId(int id) {

        return taskdao.getByProjectId(id);
    }

    @Override
    @Transactional
    public List<TaskDetails> getAllTask() {
        return taskdao.getAllTask();
    }

    // return employee name from manager email
    @Override
    @Transactional
    public List<String> getEmpNameList(String email) {
        List<String> li = new ArrayList<String>();
        li = taskdao.getEmpNameList(email);
        System.out.println("List of EmployeeEMail:::::::::::" + li);
        return li;
    }

    @Override
    @Transactional
    public TaskDetails getTask(int task_id) {
        return taskdao.getTask(task_id);
    }

    @Override
    @Transactional
    public void deleteTask(int task_id) {
        taskdao.deleteTask(task_id);
    }

    @Override
    @Transactional
    public List<TaskDetails> getTaskList(int mgrId) {
        return taskdao.getTaskList(mgrId);
    }

    @Override
    @Transactional
    public List<TaskDTO> getEmpTasklist(String email) {
        List<TaskDTO> obj = new ArrayList<TaskDTO>();
        obj = taskdao.getEmpTasklist(email);
        System.out.println("From Service list of empTask::::   " + obj);
        return obj;
    }

    @Override
    @Transactional
    public void updatetask1(String date, String email, int tid, String st) {
        taskdao.updatetask1(date, email, tid, st);
    }

    @Override
    @Transactional
    public String getManagerEmail(String email) {

        return taskdao.getManagerEmail(email);

    }

    @Override
    @Transactional
    public String getEmployeeName(String name) {
        return taskdao.getEmployeeName(name);

    }

    @Override
    @Transactional
    public String getProjectName(int id) {

        return taskdao.getProjectName(id);
    }

//  return managereName id
    @Override
    @Transactional
    public String getManagerName(String email) {
        return taskdao.getManagerName(email);
    }

    @Override
    @Transactional
    public String EmployeeEmail(String name) {
        return taskdao.EmployeeEmail(name);
    }

}

/*
 * @Override public List<ProjectDetails> getempTaskList(String email) {
 * List<ProjectDetails> li = new ArrayList<ProjectDetails>(); li =
 * taskdao.getempTaskList(email);
 * System.out.println("List of Employe:::: task:::::    " +li); return li; }
 */
