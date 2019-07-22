package com.kgate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.model.Skill;
import com.kgate.service.SkillService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SkillController {

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView getPage(@ModelAttribute Skill skill) {
        ModelAndView view = new ModelAndView("test2");
        return view;
    }

//    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
//    public @ResponseBody
//    Skill postEmployeeData(@RequestBody Skill skill) {
//        ModelAndView model = new ModelAndView();
//        System.out.println("test..............." + skill);
//        skillService.addSkill(skill);
//        List<Skill> listSkill = skillService.getAllSkills();
//        model.addObject("listSkill", listSkill);
//        return skill;
//    }
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map<String, Object> getSaved(Skill skill) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("View Skill::::   " + skill);
        skillService.addSkill(skill);

        if (1 == 1) {
            map.put("status", "200");
            map.put("message", "Your record have been saved successfully");
        }

        return map;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getAll(Skill skill) {
        Map<String, Object> map = new HashMap<String, Object>();

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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> delete(Skill skill) {
        Map<String, Object> map = new HashMap<String, Object>();
        skillService.deleteSkill(Integer.SIZE);
        if (true) {
            map.put("status", "200");
            map.put("message", "Your record have been deleted successfully");
        }

        return map;
    }
}
