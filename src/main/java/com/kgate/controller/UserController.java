package com.kgate.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.model.Employee;
import com.kgate.model.Holiday;
import com.kgate.model.ProjectDetails;
import com.kgate.model.Skill;
import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.LoginService2;
import com.kgate.service.ProjectService;
import com.kgate.service.SkillService;
import com.kgate.service.TaskService;

@Controller
public class UserController {


    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private TaskService taskservice;

    @Autowired
    private LoginService2 loginservice2;

    @Autowired
    private SkillService skillService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectservice;

    @Autowired
    private Employee emp;

    public void setloginService1(LoginService2 loginservice2) {
        this.loginservice2 = loginservice2;
    }

    public void setemployeeservice(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView("login");
//        Employee employee = new Employee();
        mav.addObject("employee", emp);
        String[] userType = {"Admin", "Employee", "Manager", "CEO"};
        mav.addObject("userTypes", userType);
        return mav;
    }

    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
    public ModelAndView editByemployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        String message = "Employee is successfully edited.";
        // ModelAndView mav = new ModelAndView("EditEmployee");
        ModelAndView mav = new ModelAndView("testfile");
        // mav.addObject("message", message);

        return mav;

    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)

    public ModelAndView authenticate(ModelMap modelMap, @ModelAttribute("employee") Employee employee,
            HttpServletRequest request, Map<String, Object> map, @RequestParam("email") String email) {

        /*
		 * validate whether person is in database and person user and password are
		 * matching
         */
        //session Related
        HttpSession session = request.getSession(false);
//        session.setMaxInactiveInterval(20);
        //
        emp.setEmail(email);
        boolean isValidUser = loginservice2.checkLogin(employee.getEmail(), employee.getPassword(),
                employee.getCategory());

        if (isValidUser) {
            if (employee.getCategory().equals("Admin")) {

                request.setAttribute("loginuser", employee.getEmail());

                ModelAndView mav = new ModelAndView("success");

                Employee e = employeeService.searchByEmail(email);

                mav.addObject("employee", e);
                session.setMaxInactiveInterval(1 * 60);

                return mav;

            } else if (employee.getCategory().equals("Manager")) {

                ModelAndView mav = new ModelAndView("ManagerDashboard");
                Employee e = employeeService.searchByEmail(email);

                mav.addObject("employee", e);
                System.out.println("Session Email:::    " + emp.getEmail());
                return mav;

            } else if (employee.getCategory().equals("Employee")) {

                ModelAndView mav = new ModelAndView("byEmployeeEdit");

                Employee emp = employeeService.searchByEmail(employee.getEmail());
                List<String> employeeSkill
                        = skillService.getEmployeeSkillByEmail(employee.getEmail());

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

            } else if (employee.getCategory().equals("CEO")) {
                ModelAndView model = new ModelAndView("CEODashboard1");
                List<ProjectDetails> listProject = projectservice.dispalyProjects();

                model.addObject("listProject", listProject);
                Employee e = employeeService.searchByEmail(email);

                model.addObject("employee", e);
                List<TaskDetails> tasklist = taskservice.getAllTask();
                model.addObject("tasklist", tasklist);
                List<Holiday> holiday=employeeService.getAllHoliday();
                model.addObject("holiday",holiday);
         return model;
            
            }

        } else {
            modelMap.put("error", "Invalid UserName / Password");
        }
        return init();

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession();
//        httpSession.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/Empedit", method = RequestMethod.POST)
    public ModelAndView Empedit(@ModelAttribute Employee employee) {
//        ModelAndView mav = new ModelAndView("ManagerEdit");
        ModelAndView mav = new ModelAndView("byEmployeeEdit");
        Employee emp = employeeService.searchByEmail(employee.getEmail());
        List<String> employeeSkill = skillService.getEmployeeSkillByEmail(employee.getEmail());

        System.out.println("List of EmployeeSkill:   " + employeeSkill);

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

    @RequestMapping(value = "/MngEdit", method = RequestMethod.POST)
    public ModelAndView MngEdit(@ModelAttribute Employee employee) {
        ModelAndView mav = new ModelAndView("ManagerEdit");
//        ModelAndView mav = new ModelAndView("byEmployeeEdit");
        Employee emp = employeeService.searchByEmail(employee.getEmail());
        List<String> employeeSkill = skillService.getEmployeeSkillByEmail(employee.getEmail());

        System.out.println("List of EmployeeSkill:   " + employeeSkill);

        List<Skill> listSkill = skillService.getAllSkills();

        List<String> sk = new ArrayList<>();

        for (int i = 0; i < employeeSkill.size(); i++) {
            Object o = employeeSkill.get(i);
            String s = (String) o;
            sk.add(s);
        }
        try{
            emp.setSkills(sk);
        }
        catch(Exception e){
            
        };
        String[] userType = {"Employee", "Admin", "Manager"};
        mav.addObject("userTypes", userType);

        mav.addObject("listSkill", listSkill);
        mav.addObject("employee", emp);

        Skill skill = new Skill();
        mav.addObject("skill", skill);
        return mav;

    }

}
