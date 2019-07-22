package com.kgate.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JQueryController {

    @RequestMapping(value = "/test/hello")
    public String helloWorld() {
        return "hello";
    }

    @RequestMapping(value = "/test/Ajax")
    @ResponseBody
    public String helloAjaxWorld() {
        Date d = new Date();
        return d.toString();
    }
}
