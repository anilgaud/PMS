package com.kgate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kgate.model.Employee;
import com.kgate.model.Skill;
import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.SkillService;
import com.kgate.service.TaskService;

@Controller
public class EditAjaxController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private Employee employee;

	@Autowired
	private TaskService taskservice;

	@RequestMapping(value = "/byEmployeeEdit1", method = RequestMethod.POST)
	public @ResponseBody void byEmployeeEdit1(@ModelAttribute Employee employee) {

		for (String skill : employee.getSkills()) {
			Skill sk = skillService.getSkillByName(skill);
			employee.getListSkill().add(sk);
		}
		employeeService.addEmployee(employee);

	}

	@RequestMapping(value = "/Empdet", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> Empdet() {
		Map<String, Object> model = new HashMap<String, Object>();

		Employee emp = employeeService.searchByEmail(employee.getEmail());
		List<String> employeeSkill = skillService.getEmployeeSkillByEmail(employee.getEmail());

		System.out.println("List of EmployeeSkill: " + employeeSkill);

		List<Skill> listSkill = skillService.getAllSkills();

		List<String> sk = new ArrayList<>();

		for (int i = 0; i < employeeSkill.size(); i++) {
			Object o = employeeSkill.get(i);
			String s = (String) o;
			sk.add(s);
		}
		emp.setSkills(sk);
		String[] userType = { "Employee", "Admin", "Manager" };

		model.put("userTypes", userType);
		model.put("listSkill", listSkill);
		model.put("employee", emp);

		Skill skill = new Skill();
		model.put("skill", skill);
		return model;

	}

	@RequestMapping(value = "createTask1", method = RequestMethod.POST)
	public @ResponseBody List<TaskDetails> createTask1(@ModelAttribute("taskdetails") TaskDetails taskdetails) {
		int pId = taskdetails.getProjectId();
		taskdetails.setProjectId(pId);

		int mId = taskdetails.getManagerId();
		taskdetails.setManagerId(mId);

		taskdetails.setStatus("Not Assigned");
		taskservice.addTask(taskdetails);

		List<TaskDetails> listtask = taskservice.getByProjectId(pId);

		return listtask;

	}

	@RequestMapping(value = "listTaskAjax", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> listTask() {
		Map<String, Object> map = new HashMap<>();
		List<TaskDetails> list = taskservice.getAllTask();
		map.put("list", list);
		return map;
	}
	
	 
}
