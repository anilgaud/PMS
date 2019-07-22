package com.kgate.controller;

import com.kgate.model.Employee;
import com.kgate.model.ProjectDetails;
import com.kgate.model.TaskDetails;
import com.kgate.service.ProjectService;
import com.kgate.service.TaskService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CProjectAjaxController {

    @Autowired
    private Employee emp;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/cProjectAjax", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> postEmployeeData(@RequestBody ProjectDetails projectDetails) {
        Map<String, Object> map = new HashMap<>();
        projectDetails.setManageremail(emp.getEmail());
        String name = projectDetails.getProject_Name();
        String projectname = projectService.findproject(name);
        Integer st = projectDetails.getProject_id();
        if (projectname == null || st != null) {
            projectService.createProject(projectDetails);
            map.put("msg", "You have successfully created Project");
        } else {
            map.put("msg", "Duplicate Project Name");
        }

        return map;
    }

    @RequestMapping(value = "/listProjectAjax", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAll(ProjectDetails projectDetails) {
        Map<String, Object> map = new HashMap<>();

        List<ProjectDetails> list = projectService.getProjectByEmail(emp.getEmail());

        if (list != null) {
            map.put("status", "200");
            map.put("message", "Data found");
            map.put("data", list);
        } else {
            map.put("status", "404");
            map.put("message", "Data not found");

        }

        return map;
    }

    @RequestMapping(value = "/delete2Project", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteProject(ProjectDetails projectDetails) {
        Map<String, Object> map = new HashMap<>();
        List<TaskDetails> li = taskService.getByProjectId(projectDetails.getProject_id());
        System.out.println("List.............." + li);
        int flag = 0;
        for (int i = 0; i < li.size(); i++) {
            TaskDetails ts = li.get(i);
            String temp = ts.getTaskStatus();
            if (temp.equals("W .I. P")) {
                flag = 1;

            } else {
                flag = 0;
            }
        }
        if (flag == 0) {
            projectService.deleteproject(projectDetails.getProject_id());
            map.put("msg", "Your project deleted successfully");
        } else {
            map.put("msg", "You can't delete this project");
        }
        return map;
    }
}
