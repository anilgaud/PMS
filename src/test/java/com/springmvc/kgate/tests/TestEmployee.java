package com.springmvc.kgate.tests;

import org.springframework.beans.factory.annotation.Autowired;
import com.kgate.dao.EmployeeDAO;
import com.kgate.dao.SkillDao;
import com.kgate.model.Skill;
import com.kgate.service.EmployeeService;
import com.kgate.service.SkillService;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {"classpath:appContext.xml"})

public class TestEmployee {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SkillService skillService;

//    @Rollback(value = false)
//    @Test
//    @Rollback(value = false)
//    public void getEmployeeSkill(){
//        int id = 34;
////        List<Skill> skill = skillDao.getEmployeeSkill(id);
////        System.out.println("Skill List:    "+skill);
//    }
    @Test
    @Rollback(value = false)
    public void getAllSkills() {
        List<Skill> li = skillService.getAllSkills();
      
    }

}
