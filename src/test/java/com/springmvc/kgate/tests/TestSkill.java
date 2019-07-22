package com.springmvc.kgate.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.service.EmployeeService;
import com.kgate.service.SkillService;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})

public class TestSkill {

    @Autowired
    EmployeeService employeeservice;

   

}
