package com.kgate.controller;

import com.kgate.model.Employee;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

	@ResponseBody
    @RequestMapping(value = "/employeeRest", method = RequestMethod.GET)
    public ArrayList<Employee> getEmployee() {

        Employee e = new Employee();
        e.setName("Gulrez");
        Employee e1 = new Employee();
        e1.setName("Farooqui");
        ArrayList<Employee> al = new ArrayList<Employee>();
        al.add(e);
        al.add(e1);
        return al;
    }

}
