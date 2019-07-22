package com.kgate.controller;

import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.service.EmployeeService;
import com.kgate.service.SkillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private EmployeeService employeeService;

   /* @GetMapping("/restSkillList")
    public ResponseEntity<List<Skill>> skillList() {
        List<Skill> slist = skillService.getAllSkills();
        return ResponseEntity.ok().body(slist);
    }
  
    @GetMapping("/restEmployeeList")
    public ResponseEntity<List<Employee>> employeelist() {
        List<Employee> empList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(empList);
    }*/
}
