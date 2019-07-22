package com.kgate.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.model.Employee;
import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;
import com.kgate.model.TaskDTO;
import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.ProjectService;
import com.kgate.service.TaskService;
import java.text.SimpleDateFormat;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SuppressWarnings("deprecation")
@Controller
public class ProjectController {

    private static final Logger logger = Logger.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectservice;

    @Autowired
    private TaskService taskservice;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Employee emp;

    @InitBinder
    public void initConverter(WebDataBinder binder) {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @RequestMapping(value = "/cproject", method = RequestMethod.POST)
    public ModelAndView createProject(@ModelAttribute("projectDetails") ProjectDetails projectDetails, @RequestParam("project_Name") String name,
            ModelMap modelMap) {
        ModelAndView model = new ModelAndView("redirect:/cproject2");

//        ModelAndView model = new ModelAndView("CreateProject");
        projectDetails.setManageremail(emp.getEmail());

        String projectname = projectservice.findproject(name);
        if (projectname == null) {
            projectservice.createProject(projectDetails);
        } else {
            ModelAndView model1 = new ModelAndView("redirect:/duplicateproject");
            return model1;
        }
        ProjectDetails pd = new ProjectDetails();
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());
        model.addObject("listProject ", listProject);
        model.addObject("pd", pd);

        return model;

    }

    @RequestMapping(value = "/duplicateproject", method = RequestMethod.GET)
    public ModelAndView duplicate(@ModelAttribute("projectDetails") ProjectDetails projectDetails, ModelMap modelMap) {
        Integer mid = projectservice.getManagerid(emp.getEmail());
        ModelAndView mav = new ModelAndView("CreateProject");
        ProjectDetails projectdetails = new ProjectDetails();
        ProjectDetails pd = new ProjectDetails();
        mav.addObject("projectdetails", projectdetails);
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());
        System.out.println("List of Project:  " + listProject);
        mav.addObject("mid", mid);
        mav.addObject("pd", pd);
        mav.addObject("listProject", listProject);
        modelMap.addAttribute("msg", "Already Exists");

        return mav;

    }

    @RequestMapping(value = "/cproject2", method = RequestMethod.GET)
    public ModelAndView createProject2(@ModelAttribute("projectDetails") ProjectDetails projectDetails, ModelMap modelMap) {
        Integer mid = projectservice.getManagerid(emp.getEmail());
        ModelAndView mav = new ModelAndView("CreateProject");
        ProjectDetails projectdetails = new ProjectDetails();
        ProjectDetails pd = new ProjectDetails();
        mav.addObject("projectdetails", projectdetails);
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());
        System.out.println("List of Project:  " + listProject);
        mav.addObject("mid", mid);
        mav.addObject("pd", pd);
        mav.addObject("listProject", listProject);

        return mav;

    }

    @RequestMapping(value = "/showtask", method = RequestMethod.GET)
    public ModelAndView showtask(@ModelAttribute("taskdetails") TaskDetails taskdetails, HttpServletRequest request) {
        int pId = Integer.parseInt(request.getParameter("project_id"));
//        int pId = taskdetails.getProjectId();
//        int mId = taskdetails.getManagerId();
        taskdetails.setProjectId(pId);
        Integer mid = projectservice.getManagerid(emp.getEmail());
//        int mId = Integer.parseInt(request.getParameter("mgrid"));
        taskdetails.setManagerId(mid);
        System.out.println("Project ID::::    " + pId + "Manager ID::::::    " + mid);

        ModelAndView mav = new ModelAndView("createtask");

        String[] Tasktype = {"Coding", "Design", "Integration", "Quality", "Testing"};
        mav.addObject("task_Type", Tasktype);
//        List<TaskDetails> listtask = taskservice.getAllTask();
        List<TaskDetails> listtask = taskservice.getByProjectId(pId);
        System.out.println("List of task:  " + listtask);
        mav.addObject("taskdetails", taskdetails);
        TaskDetails td = new TaskDetails();
        mav.addObject("td", td);
        mav.addObject("listtask", listtask);
        Employee e = new Employee();
        mav.addObject("e", employeeService.searchByEmail(emp.getEmail()));
        mav.addObject("em", emp.getEmail());

        return mav;
    }

    /*
	 * @RequestMapping(value = "/delete", method = RequestMethod.GET) public
	 * ModelAndView deleteTask(HttpServletRequest request) { int task_id =
	 * Integer.parseInt(request.getParameter("taskid"));
	 * taskservice.deleteTask(task_id); tring[] Tasktype=
	 * {"Coding","Design","Integration","Quality","Testing"};
	 * mav.addObject("task_Type",Tasktype); List<TaskDetails> listtask
	 * =taskservice.getByProjectId(pId); System.out.println("List of task:  " +
	 * listtask); mav.addObject("td", taskdetails); mav.addObject("listtask",
	 * listtask); return new ModelAndView("deletetask"); }
     */

 /*
	 * @RequestMapping(value = "/delete1", method = RequestMethod.GET)
	 * 
	 * public ModelAndView deleteTask(HttpServletRequest request) { int task_id =
	 * Integer.parseInt(request.getParameter("taskid"));
	 * taskservice.deleteTask(task_id); tring[] Tasktype=
	 * {"Coding","Design","Integration","Quality","Testing"};
	 * mav.addObject("task_Type",Tasktype); List<TaskDetails> listtask
	 * =taskservice.getByProjectId(pId); System.out.println("List of task:  " +
	 * listtask); mav.addObject("td", taskdetails); mav.addObject("listtask",
	 * listtask); return new ModelAndView("redirect:/deleteTask"); }
     */
    @RequestMapping(value = "/backtoproject", method = RequestMethod.GET)
    public ModelAndView backtoproject(@ModelAttribute("employee") Employee employee,
            @RequestParam("email") String email) {

        ModelAndView mav = new ModelAndView("CreateProject");

        Integer mid = projectservice.getManagerid(email);
        mav.addObject("mid", mid);

        ProjectDetails pd = new ProjectDetails();
        mav.addObject("pd", pd);

        TaskDetails taskdetails = new TaskDetails();
        mav.addObject("taskdetails", taskdetails);

        Employee e = new Employee();
        e = employeeService.searchByEmail(employee.getEmail());
        mav.addObject("e", e);
        taskdetails.setEmp_Email(employee.getEmail());
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(employee.getEmail());
        System.out.println("List of Project:  " + listProject);
        mav.addObject("listProject", listProject);

        return mav;

    }

    @RequestMapping(value = "/backtotask", method = RequestMethod.POST)
    public ModelAndView backtotask(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("createtask");

        String s = request.getParameter("em");
//    	Integer mid = projectservice.getManagerid(email);
        Integer mid = projectservice.getManagerid(s);
        mav.addObject("mid", mid);

        ProjectDetails pd = new ProjectDetails();
        mav.addObject("pd", pd);

        TaskDetails taskdetails = new TaskDetails();
        mav.addObject("taskdetails", taskdetails);

        Employee e = new Employee();
        e = employeeService.searchByEmail(s);
        System.out.println("Email:::::    " + e);
        mav.addObject("td", e);

        String[] Tasktype = {"Coding", "Design", "Integration", "Quality", "Testing"};
        mav.addObject("task_Type", Tasktype);
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(s);
        System.out.println("List of Project:  " + listProject);
        mav.addObject("listProject", listProject);
        /*List<TaskDetails> listtask = taskservice.getByProjectId(pId);
		System.out.println("List of task:  " + listtask);
		mav.addObject("td", taskdetails);
		mav.addObject("listtask", listtask);*/
//		List<TaskDetails> listTask = taskservice.getTaskList(mid);
//		System.out.println("List of Task:" + listTask);
//		mav.addObject("listTask", listTask);

        return mav;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteTask(HttpServletRequest request, @ModelAttribute("taskdetails") TaskDetails taskdetails) {
        ModelAndView mav = new ModelAndView("createtask");
        int task_id = Integer.parseInt(request.getParameter("task_id"));
        taskservice.deleteTask(task_id);
        Integer mid = projectservice.getManagerid(emp.getEmail());
        taskdetails.setManagerId(mid);
        mav.addObject("e", employeeService.searchByEmail(emp.getEmail()));
        String[] Tasktype = {"Coding", "Design", "Integration", "Quality", "Testing"};
        mav.addObject("task_Type", Tasktype);
        int pId = Integer.parseInt(request.getParameter("project_id"));
        List<TaskDetails> listtask = taskservice.getByProjectId(pId);
        taskdetails.setProjectId(pId);
        TaskDetails td = new TaskDetails();
        mav.addObject("td", td);
        mav.addObject("listtask", listtask);
        mav.addObject("taskdetails", taskdetails);
        String s = request.getParameter("em");
        mav.addObject("em", s);
        return mav;
    }

    @RequestMapping(value = "/createtask", method = RequestMethod.POST)
    public ModelAndView taskcreate(@ModelAttribute("taskdetails") TaskDetails taskdetails, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("createtask");

        int pId = taskdetails.getProjectId();
        taskdetails.setProjectId(pId);

        int mId = taskdetails.getManagerId();
        taskdetails.setManagerId(mId);

        taskdetails.setStatus("Not Assigned");
        taskservice.addTask(taskdetails);
        String[] Tasktype = {"Coding", "Design", "Integration", "Quality", "Testing"};
        mav.addObject("task_Type", Tasktype);
        List<TaskDetails> listtask = taskservice.getByProjectId(pId);
        System.out.println("List of task:  " + listtask);
        mav.addObject("td", taskdetails);
        mav.addObject("listtask", listtask);
        Employee e1 = new Employee();
        String s = request.getParameter("em");
        mav.addObject("em", s);

        mav.addObject("e", employeeService.searchByEmail(emp.getEmail()));

        return mav;
    }

//    @RequestMapping(value = "/createtask2", method = RequestMethod.GET)
//    public ModelAndView taskcreatee(@ModelAttribute("taskdetails") TaskDetails taskdetails, HttpServletRequest request) {
//        int pId = taskdetails.getProjectId();
//        taskdetails.setProjectId(pId);
//        int mId = taskdetails.getManagerId();
//        taskdetails.setManagerId(mId);
//        ModelAndView mav = new ModelAndView("createtask");
//        taskdetails.setStatus("Not Assigned");
//        taskservice.addTask(taskdetails);
//        String[] Tasktype = {"Coding", "Design", "Integration", "Quality", "Testing"};
//        mav.addObject("task_Type", Tasktype);
//        List<TaskDetails> listtask = taskservice.getByProjectId(pId);
//        System.out.println("List of task:  " + listtask);
//        mav.addObject("td", taskdetails);
//        mav.addObject("listtask", listtask);
//        return mav;
//    }
    // CEO Project related
    @RequestMapping(value = "/displayProjectDetails", method = RequestMethod.GET)
    public ModelAndView displayProjectDetails(@RequestParam("project_id") String project_id,
            HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("project_id"));
//        System.out.println("Project Name::::    " + projectName);
        System.out.println("Project Id :: " + project_id);
        ModelAndView mav = new ModelAndView("projectStatus");

        List<TaskDTO> listProject = null;

        try {
            listProject = projectservice.displayAllStatus(id);
            System.out.println("EmployeeName;::::::::  " + listProject.get(0).getEmp_name());
            System.out.println("List of Project::::::   " + listProject);

            mav.addObject("listProject", listProject);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bound Exception Occured::: " + e);
        }
        TaskDTO d = new TaskDTO();
        mav.addObject("Pid", project_id);
        mav.addObject("d", d);

        return mav;
    }

    @RequestMapping(value = "/downloadReport", method = RequestMethod.POST)
    public ModelAndView downloadReport(@RequestParam("project_id") String project_id, HttpServletRequest request) {
//         int project_id = Integer.parseInt(request.getParameter("project_id"));
//        int project_id = (Integer.BYTES).project_id;
        int id = Integer.parseInt(project_id);
        List<TaskDTO> listProject = projectservice.displayAllStatus(id);
        return new ModelAndView("pdfReport", "listProject", listProject);
    }

    @RequestMapping(value = "/downloadProjectReport", method = RequestMethod.POST)
    public ModelAndView downloadProjectReport(HttpServletRequest request) {
        List<ProjectReportDTO> listProjectStatus = projectservice.listProjectReport();
        System.out.println("List::::   " + listProjectStatus);
//        for (ProjectReportDTO listProjectStatu : listProjectStatus) {
//            String projectName = listProjectStatu.getProject_name();
//            System.out.println("ProjectName::::::::::::::::::::::::::::::::" + projectName);
//    }
//        System.out.println("List of Project Status:::" + listProjectStatus.get(0));
//        System.out.println("List project Details::::   " + listProjectStatus.get(0).getProject_name());
//        System.out.println("List project Details::::   " + listProjectStatus.get(0).getpStartDate());
//        System.out.println("List project Details::::   " + listProjectStatus.get(0).getpEndDate());
//        List<ProjectDetails> listProject = projectservice.dispalyProjects();
        return new ModelAndView("pdfProjectReport", "listProject", listProjectStatus);

    }

    @RequestMapping(value = "/CeoBack", method = RequestMethod.POST)

    public ModelAndView back() {
        ModelAndView model = new ModelAndView("CEODashboard1");

        List<ProjectDetails> listProject = projectservice.dispalyProjects();

        model.addObject("listProject", listProject);

        List<TaskDetails> tasklist = taskservice.getAllTask();
        model.addObject("tasklist", tasklist);
        return model;

    }

    @RequestMapping(value = "/createProject2", method = RequestMethod.POST)
    public ModelAndView createProject2() {
//        ModelAndView mav = new ModelAndView("CreateProject");
        ModelAndView mav = new ModelAndView("createProject2");
        Integer mid = projectservice.getManagerid(emp.getEmail());

        mav.addObject("mid", mid);
        ProjectDetails projectdetails = new ProjectDetails();
        projectdetails.setManageremail(emp.getEmail());
        TaskDetails taskdetails = new TaskDetails();
        ProjectDetails pd = new ProjectDetails();
        mav.addObject("projectdetails", projectdetails);
        taskdetails.setEmp_Email(emp.getEmail());
        mav.addObject("taskdetails", taskdetails);
        Employee e = new Employee();
        mav.addObject("e", employeeService.searchByEmail(emp.getEmail()));
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());

        mav.addObject("pd", pd);

        mav.addObject("listProject", listProject);
        return mav;

    }

    @RequestMapping(value = "/empproject", method = RequestMethod.POST)
    public ModelAndView EmpProjectStatus(@SessionAttribute("employee") Employee employee, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("employeeProjectStatus");
//        int id = Integer.parseInt("project_id");
        List<TaskDTO> taskDTOs = projectservice.displayAllStatus2(employee.getEmail(), 9);
        TaskDTO dTO = new TaskDTO();
        System.out.println("TaskStatus::::" + taskDTOs);
        mav.addObject("dTO", dTO);
        mav.addObject("taskDTOs", taskDTOs);
        return mav;

    }

    @RequestMapping(value = "/downloadProjectStatus", method = RequestMethod.POST)
    public ModelAndView downloadProjectStatus(@RequestParam("project_id") String project_id) {
        int id = Integer.parseInt(project_id);
        System.out.println("Ide:: " + id);
        List<TaskDTO> list = projectservice.displayAllStatus2(emp.getEmail(), id);
        ModelAndView mav = new ModelAndView("pdfProjectStatus");
        mav.addObject("listProject", list);
//        return new ModelAndView("pdfProjectStatus", "listProject", list);
        return mav;
    }

    @RequestMapping(value = "/managerProjectView", method = RequestMethod.POST)
    public ModelAndView managerProjectView() {
        ModelAndView mav = new ModelAndView("managerProjectDetails");
        List<ProjectDetails> listProjectName = projectservice.getProjectByEmail(emp.getEmail());

        ProjectDetails pd = new ProjectDetails();
        mav.addObject("listProjectName", listProjectName);
        mav.addObject("pd", pd);
        System.out.println("List Project Name::::::  " + listProjectName);
//        List<TaskDTO> taskDTOs = projectservice.displayAllStatus2(employee.getEmail());
        TaskDTO dTO = new TaskDTO();
//        System.out.println("TaskStatus::::" + taskDTOs);
        mav.addObject("dTO", dTO);
//        mav.addObject("taskDTOs", taskDTOs);
        return mav;

    }

    @RequestMapping(value = "/viewProject", method = RequestMethod.GET)
    public ModelAndView viewProject(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("employeeProjectStatus");
        int id = Integer.parseInt(request.getParameter("project_id"));
        List<TaskDTO> taskDTOs = projectservice.displayAllStatus2(emp.getEmail(), id);
        TaskDTO dTO = new TaskDTO();
        System.out.println("TaskStatus::::" + taskDTOs);
        mav.addObject("dTO", dTO);
        mav.addObject("taskDTOs", taskDTOs);
        mav.addObject("project_id", id);
        return mav;

    }

    @RequestMapping(value = "/overviewProject", method = RequestMethod.GET)
    public ModelAndView overviewProject(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("overviewProjectStatus");
        int id = Integer.parseInt(request.getParameter("project_id"));
        List<TaskDTO> taskDTOs = projectservice.displayAllStatus3(emp.getEmail(), id);
        TaskDTO dTO = new TaskDTO();
        System.out.println("TaskStatus::::" + taskDTOs);
        mav.addObject("dTO", dTO);
        mav.addObject("taskDTOs", taskDTOs);
        mav.addObject("project_id", id);
        return mav;

    }

    @RequestMapping(value = "/downloadOverviewProjectStatus", method = RequestMethod.POST)
    public ModelAndView downloadOverviewProjectStatus(@RequestParam("project_id") String project_id) {
        int id = Integer.parseInt(project_id);
        List<TaskDTO> list = projectservice.displayAllStatus3(emp.getEmail(), id);
        ModelAndView mav = new ModelAndView("pdfProjectStatusOverview");
        mav.addObject("listProject", list);
//        return new ModelAndView("pdfProjectStatus", "listProject", list);
        return mav;
    }

    @RequestMapping(value = "/ManagerDashboardBack", method = RequestMethod.POST)
    public ModelAndView ManagerDashboardBack(@ModelAttribute("employee") Employee employee) {
        ModelAndView mav = new ModelAndView("ManagerDashboard");

        return mav;
    }

    @RequestMapping(value = "/ManagerProjectDetBack", method = RequestMethod.POST)
    public ModelAndView ManagerProjectDetBack(@ModelAttribute("employee") Employee employee) {
        ModelAndView mav = new ModelAndView("managerProjectDetails");
        List<ProjectDetails> listProjectName = projectservice.getProjectByEmail(emp.getEmail());

        ProjectDetails pd = new ProjectDetails();
        mav.addObject("listProjectName", listProjectName);
        mav.addObject("pd", pd);
        return mav;
    }

    @RequestMapping(value = "/deleteproject", method = RequestMethod.GET)
    public ModelAndView deleteproject(HttpServletRequest request, @ModelAttribute("projectDetails") ProjectDetails projectDetails) {
        int id = Integer.parseInt(request.getParameter("project_id"));
        projectservice.deleteproject(id);
        ModelAndView model = new ModelAndView("redirect:/cproject2");

//        ModelAndView model = new ModelAndView("CreateProject");
        projectDetails.setManageremail(emp.getEmail());
        ProjectDetails pd = new ProjectDetails();
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());
        model.addObject("listProject ", listProject);
        model.addObject("pd", pd);

        return model;

    }

    @RequestMapping(value = "/editproject", method = RequestMethod.GET)
    public ModelAndView editproject(HttpServletRequest request, @ModelAttribute("projectDetails") ProjectDetails projectDetails) {
        ModelAndView mav = new ModelAndView("editProject");
        int id = Integer.parseInt(request.getParameter("project_id"));
        Integer mid = projectservice.getManagerid(emp.getEmail());
        ProjectDetails prd = projectservice.getProjectById(id);

        mav.addObject("mid", mid);
        ProjectDetails projectdetails = new ProjectDetails();
        projectdetails.setManageremail(emp.getEmail());

        ProjectDetails pd = new ProjectDetails();
        mav.addObject("projectdetails", projectdetails);
        mav.addObject("pd", prd);
        return mav;

    }

    @RequestMapping(value = "/editproject1", method = RequestMethod.POST)
    public ModelAndView editproject1(HttpServletRequest request, @ModelAttribute("projectDetails") ProjectDetails projectDetails, ModelMap modelMap,
            @RequestParam("project_Name") String name) {

        ModelAndView model = new ModelAndView("editProject");
        String projectname = projectservice.findproject(name);
        if (projectname == null) {
            projectservice.createProject(projectDetails);
        } else {
            ModelAndView model1 = new ModelAndView("redirect:/duplicateproject");
            return model1;
        }
//       ModelAndView model = new ModelAndView("CreateProject");
        projectDetails.setManageremail(emp.getEmail());
        ProjectDetails pd = new ProjectDetails();
        List<ProjectDetails> listProject = projectservice.getProjectByEmail(emp.getEmail());
        model.addObject("listProject ", listProject);
        model.addObject("pd", pd);
        modelMap.addAttribute("msg", "You have successfully edited");
        return model;

    }

    @RequestMapping(value = "/getProjecttask")
    @ResponseBody()
    public int getProjecttask(HttpServletRequest request) {
        String s = request.getParameter("pid");
        int id = Integer.parseInt(s);
        List<TaskDetails> tl = taskservice.getByProjectId(id);
        for (int i = 0; i < tl.size(); i++) {
            TaskDetails s1 = tl.get(i);
            String p = s1.getTaskStatus();
            if (p.equals("W .I. P")) {
                return 1;
            }
        }
        return 0;

    }

}
