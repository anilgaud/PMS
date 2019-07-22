package com.kgate.controller;

import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.service.SkillService;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	private static final Logger logger = Logger.getLogger(TestController.class);

//     @RequestMapping(value = "/test")
//     public ModelAndView viewSkill(){
//         return new ModelAndView("test");
//     }
    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Skill skill = new Skill();
        List<Skill> listSkill = skillService.getAllSkills();
        model.addObject("listSkill", listSkill);
        model.addObject("skill", skill);
        model.setViewName("test");
//        model.addObject("error", error);
        return model;
    }

    @RequestMapping(value = "/saveTest", method = RequestMethod.POST)
    public ModelAndView saveSkill(@ModelAttribute Skill skill, @RequestParam("skill_name") String skill_name) {

        ModelAndView model = new ModelAndView("redirect:/test");
        String error = "Duplicate Skill";

        String skillName = skillService.findSkill(skill_name);
        if (skillName == null) {
            skillService.addSkill(skill);

        } else {
            model.addObject("error", error);
        }

        return TestController.this.newContact(model);

    }

    @RequestMapping(value = "/deleteTest", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        int skill_Id = Integer.parseInt(request.getParameter("skill_Id"));
        skillService.deleteSkill(skill_Id);
        //  employeeService.deleteEmployee(employeeId);
        return new ModelAndView("redirect:/test");
    }

    @RequestMapping(value = "/editTest", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int skill_Id = Integer.parseInt(request.getParameter("skill_Id"));
        Skill skill = skillService.getSkill(skill_Id);
        //  Employee employee = employeeService.getEmployee(employeeId);
        ModelAndView model = new ModelAndView("editSkill");
        model.addObject("skill", skill);

        return model;
    }

    @RequestMapping(value = "/backtosuccess", method = RequestMethod.POST)

    public ModelAndView backtosuccess() {

        ModelAndView mav = new ModelAndView("success");
        return mav;

    }

}
