package com.kgate.controller;

import com.kgate.model.Employee;
import com.kgate.model.SessionScopBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BeanScopeController {

    @Autowired
    private Employee emp;

    @RequestMapping(value = "/sess")
    public ModelAndView ViewBean() {
        ModelAndView model = new ModelAndView("check");
//        SessionScopBean ssb = new SessionScopBean();
        emp.setName("Hello Employee Controller");
        model.addObject("scopBean", emp);
        return model;
    }
     @RequestMapping(value = "/sess2")
    public ModelAndView ViewBea2n() {
        ModelAndView model = new ModelAndView("check2");
//        SessionScopBean ssb = new SessionScopBean();
        emp.getName();
        model.addObject("scopBean", emp);
        return model;
    }
}
