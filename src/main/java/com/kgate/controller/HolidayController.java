package com.kgate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.model.Holiday;
import com.kgate.service.EmployeeService;

@Controller
public class HolidayController {

	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/Holiday", method = RequestMethod.GET)
	public ModelAndView viewHoliday() {
		ModelAndView mav = new ModelAndView("Holiday");
		Holiday holiday = new Holiday();
		mav.addObject("holiday", holiday);
		List<Holiday> listholiDays = employeeService.getAllHoliday();
		System.out.println("list of HOlidays" + listholiDays);
		mav.addObject("listholiDays", listholiDays);
		return mav;
	}

	@RequestMapping(value = "/Addholiday", method = RequestMethod.POST)
	public ModelAndView addholiday(@ModelAttribute("holiday") Holiday holiday) {
		ModelAndView mav = new ModelAndView("redirect:/Holiday");
		employeeService.addHoliday(holiday);
		List<Holiday> listholiDays = employeeService.getAllHoliday();
		System.out.println("list of HOlidays" + listholiDays);
		mav.addObject("listholiDays", listholiDays);
		return mav;
	}

	@RequestMapping(value = "/deleteHoliday", method = RequestMethod.GET)
	public ModelAndView deleteHoliday(@ModelAttribute("holiday") Holiday holiday, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("daysId"));
		ModelAndView mav = new ModelAndView("redirect:/Holiday");
		employeeService.deleteHoliday(id);
		List<Holiday> listholiDays = employeeService.getAllHoliday();
		System.out.println("list of HOlidays" + listholiDays);
		mav.addObject("listholiDays", listholiDays);
		return mav;
	}
	
	@RequestMapping(value="/adminback", method=RequestMethod.POST)
	public ModelAndView back()
	{
		 ModelAndView mav = new ModelAndView("success");
         return mav;
	}
}
