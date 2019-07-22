package com.kgate.controller;

import com.kgate.model.Skill;
import com.kgate.service.SkillService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpAjaxController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/testAjax")
    public ModelAndView viewPage(@ModelAttribute Skill skill) {
        ModelAndView model = new ModelAndView("test");
        model.addObject("listSkill", skillService.getAllSkills());
        return model;
    }

    @RequestMapping(value = "/SaveSkillAjax", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> postEmployeeData(@RequestBody Skill skill) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("test..............." + skill);
         String skillName = skillService.findSkill(skill.getSkill_name());
        if (skillName == null) {
            skillService.addSkill(skill);
            map.put("message", "Your record have been saved successfully");
        } else {
            map.put("message", "Duplicate Skill");
        }
        //
        return map;
    }

    @RequestMapping(value = "/save2", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, Object> getSaved(Skill skill) {
        Map<String, Object> map = new HashMap<>();
        skillService.addSkill(skill);

        return map;
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getAll(Skill skill) {
        Map<String, Object> map = new HashMap<>();

        List<Skill> list = skillService.getAllSkills();

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

    @RequestMapping(value = "/delete2", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> delete(Skill skill) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("delete skill...........");
        skillService.deleteSkill(skill.getSkill_Id());
        return map;
    }

}
