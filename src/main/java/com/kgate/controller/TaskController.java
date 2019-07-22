package com.kgate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.model.TaskDTO;
import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.ProjectService;
import com.kgate.service.SkillService;
import com.kgate.service.TaskService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class TaskController {

    private static final Logger logger = Logger.getLogger(TaskController.class);

    @Autowired
    TaskService taskservice;

    @Autowired
    SkillService skillService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    private Employee emp;

    @InitBinder
    public void initConverter(WebDataBinder binder) {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.POST)
    public ModelAndView updateTask(@ModelAttribute("taskdetails") TaskDetails taskdetails, HttpServletRequest request) {

//        ModelAndView mav = new ModelAndView("EmployeeDashboard1");
        ModelAndView mav = new ModelAndView("redirect:/returnTask");
        String st = taskdetails.getStatus();
        String st1 = st.split(",")[0];

        List<TaskDetails> tlist = taskservice.getalltaskdetails(taskdetails.getEmp_Email());
        List<TaskDTO> tdto = taskservice.getEmpTasklist(taskdetails.getEmp_Email());
        String[] taskStatus = {"W.I.P.", "complete"};
        mav.addObject("taskStatus", taskStatus);
        mav.addObject("tobj", tdto);
        /* mav.addObject(employee); */
        Employee employee1 = new Employee();
        employee1.setEmail(taskdetails.getEmp_Email());
        mav.addObject(employee1);
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = sdf.format(d1);

        taskservice.updatetask1(date1, taskdetails.getEmp_Email(), taskdetails.getTask_id(), st1);

        String Mangemail = taskservice.getManagerEmail(emp.getEmail());
        System.out.println("Manager Email::::" + Mangemail);
        String empName = taskservice.getEmployeeName(emp.getEmail());
        System.out.println("Employee Name;::" + empName);

        String ManagerName = taskservice.getManagerName(emp.getEmail());
        System.out.println("Manager Name:::" + ManagerName);
        String projectName = taskservice.getProjectName(taskdetails.getTask_id());
        System.out.println("TaskName;::::: " + projectName);
        TaskController tc = new TaskController();
        String message = "Dear Sir/Madam"
                + "<i>Task has been completed</i><br>";
//		 message += "<font color=red>Task Completed</font>";
        message += "<table border='1'><th>Employee Name</th><th>Manager Name</th><th>Project Name</th><th>Task Name</th><th>Task Type</th><th>Task Start Date</th><th>Task End Date</th><th>Task Status</th><tr><td>"
                + empName + "</td><td>" + ManagerName + "</td><td>" + projectName + "</td><td>"
                + taskdetails.getTask_Name() + "</td><td>" + taskdetails.getTask_Type() + "</td><td> "
                + taskdetails.gettStart_Time() + "</td><td>" + taskdetails.gettEnd_Time() + "</td><td>" + st1
                + "</td></tr></table><br>"
                + "<br>"
                + "<br>"
                + "Thanks & Regards<br>"
                + empName;
        ;

        tc.sendMail(Mangemail, message, "Task has been completed");

        return mav;

    }

    @RequestMapping(value = "/editlink1", method = RequestMethod.GET)
    public ModelAndView updateTask2(@ModelAttribute("taskdetails") TaskDetails taskdetails,
            @ModelAttribute("employee") Employee employee, @RequestParam("tid") int tid, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("EmployeTaskEdit");
        String mail = request.getParameter("mail");
        List<TaskDetails> tlist = taskservice.getalltaskdetails(mail);
        mav.addObject("tlist", tlist);
        int tid1 = Integer.parseInt(request.getParameter("tid"));
        taskdetails = taskservice.getEmployeeTask(tid1);
        mav.addObject("taskdetails", taskdetails);
        String[] taskStatus = {"W.I.P.", "complete"};
        mav.addObject("taskStatus", taskStatus);
        return mav;

    }

    @RequestMapping(value = "/etasklist", method = RequestMethod.POST)
    public ModelAndView employeeTaskList(@ModelAttribute("taskdetails") TaskDetails taskdetails,
            @RequestParam("email") String email, @ModelAttribute("employee") Employee employee,
            @RequestParam("status") String status, ModelMap modelMap) {

        ModelAndView mav = new ModelAndView("EmployeeDashboard1");

        // System.out.println("List of EmployeeSkill: " + employeeSkill);
        if (status.equals("Approved")) {
            taskdetails = new TaskDetails();
            mav.addObject("taskdetails", taskdetails);
//            List<TaskDetails> tlist = taskservice.getalltaskdetails(email);
//            System.out.println("From Controller:::: list of :::   " + tlist);
            List<TaskDTO> tobj = new ArrayList<TaskDTO>();
            tobj = taskservice.getEmpTasklist(email);
            System.out.println("From Controller:::: list of Object:::   " + tobj);
            mav.addObject("tobj", tobj);
            TaskDTO taskObj = new TaskDTO();
            mav.addObject("taskObj", taskObj);
//            mav.addObject("tlist", tlist);
            mav.addObject("mail", email);
            String[] taskStatus = {"W.I.P.", "Complete"};
            mav.addObject("taskStatus", taskStatus);

            mav.addObject("employee", employee);
        } else {

            modelMap.put("error", "Please Edit Details to Make Status Approved");
            mav = new ModelAndView("byEmployeeEdit");

            Employee emp = employeeService.searchByEmail(employee.getEmail());
            List<String> employeeSkill = skillService.getEmployeeSkillByEmail(employee.getEmail());

            System.out.println("List of EmployeeSkill: " + employeeSkill);

            List<Skill> listSkill = skillService.getAllSkills();

            List<String> sk = new ArrayList<>();

            for (int i = 0; i < employeeSkill.size(); i++) {
                Object o = employeeSkill.get(i);
                String s = (String) o;
                sk.add(s);
            }
            emp.setSkills(sk);
            String[] userType = {"Employee", "Admin", "Manager"};
            mav.addObject("userTypes", userType);

            mav.addObject("listSkill", listSkill);
            mav.addObject("employee", emp);

            Skill skill = new Skill();
            mav.addObject("skill", skill);

            return mav;
        }

        return mav;

    }

    @RequestMapping(value = "/returnTask", method = RequestMethod.GET)
    public ModelAndView returnTasklist(@ModelAttribute("taskdetails") TaskDetails taskdetails) {
        ModelAndView mav = new ModelAndView("EmployeeDashboard1");
        String empEmail = emp.getEmail();
        List<TaskDTO> tobj = new ArrayList<TaskDTO>();
        emp = employeeService.searchByEmail(empEmail);
        tobj = taskservice.getEmpTasklist(empEmail);
        System.out.println("From Controller:::: list of Object:::   " + tobj);
        mav.addObject("employee", emp);
        mav.addObject("tobj", tobj);
        mav.addObject("email" + empEmail);
        mav.addObject("taskdetails", taskdetails);

        return mav;
    }

    public void sendMail(String to, String message, String subject) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gulfarooqui1@gmail.com", "Gulrez#7326");
            }
        });

        Message message1 = new MimeMessage(session);

        try {

            message1.setFrom(new InternetAddress("test@gmail.com"));
            message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message1.setSubject(subject);
            /* message1.setText(message); */
            message1.setContent(message, "text/html");
            Transport.send(message1);

            System.out.println("Done");

        } catch (MessagingException e1) {
            throw new RuntimeException(e1);
        }
        // return "employeelist";

    }
}
