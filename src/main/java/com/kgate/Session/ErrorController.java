package com.kgate.Session;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping(value = "/500")
    public String handleException(HttpServletRequest req) {
        Throwable t = (Throwable) req.getAttribute("javax.servlet.error.exception");
        return "redirect:/login";
    }
}
