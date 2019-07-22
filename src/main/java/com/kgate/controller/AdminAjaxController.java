package com.kgate.controller;

import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.SkillService;
import com.kgate.service.TaskService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminAjaxController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/employeelist2", method = RequestMethod.GET)
    public ModelAndView viewlist() {
        ModelAndView model = new ModelAndView("home2");
        return model;
    }

    @RequestMapping(value = "/listEmp", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> viewEmplist(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int page = Integer.parseInt(request.getParameter("page"));
        List<Employee> list3 = employeeService.getAllEmployees();
        int pageNo = (list3.size() / 3);
        int tempPage = list3.size() % 3;
        if (tempPage != 0) {
            pageNo = pageNo + 1;
        }
        List<Employee> list = employeeService.getAllEmployees(page);
        map.put("list", list);
        map.put("pno", pageNo);
        return map;
    }

    @RequestMapping(value = "/editEmployeeAjax", method = RequestMethod.GET)
    public ModelAndView editEmployee(HttpServletRequest request) {
        int empId = Integer.parseInt(request.getParameter("id"));
        List<String> empSkill = skillService.getEmployeeSkill(empId);
        Employee employee = employeeService.getEmployee(empId);
        ModelAndView model = new ModelAndView("edit2");
        List<Skill> listAllSkill = skillService.getAllSkills();
        List<String> sk = new ArrayList<>();
        for (int i = 0; i < empSkill.size(); i++) {
            Object o = empSkill.get(i);
            String s = (String) o;
            sk.add(s);
        }
        employee.setSkills(sk);
        String[] userType = {"Employee", "Admin", "Manager"};
        model.addObject("userTypes", userType);
        model.addObject("listSkill", listAllSkill);
        model.addObject("employee", employee);
        Skill skill = new Skill();
        model.addObject("skill", skill);
        return model;
    }

    @RequestMapping(value = "/editEmployeeAjax", method = RequestMethod.POST)
    public ModelAndView editEmployee2(@ModelAttribute Employee employee) {
        ModelAndView model = new ModelAndView("home2");
        for (String skill : employee.getSkills()) {
            Skill sk = skillService.getSkillByName(skill);
            employee.getListSkill().add(sk);
        }
        employeeService.addEmployee(employee);
        return model;
    }

    @RequestMapping(value = "/deleteEmployeeAjax", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> deleteEmployee(@RequestBody Employee employee) {
        Map<String, Object> map = new HashMap<>();
        List<TaskDetails> li = taskService.getalltaskdetails(employee.getEmail());
        if (li.size() > 0) {
            map.put("msg", "You can't delete this employee");
        } else {
            employeeService.deleteEmployee(employee.getId());
            map.put("msg", "You have successfully deleted this Employee");
        }
        return map;
    }
 
}
