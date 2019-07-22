package com.kgate.service;

import java.util.List;

import com.kgate.model.Employee;
import com.kgate.model.Holiday;
import com.kgate.model.Skill;

public interface EmployeeService {

	public List<Employee> searchEmployees(String txt);

	public List<Employee> searchEmployeesBySkill(String skill);

	public void addEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	
	public List<Employee> getAllEmployees(Integer page);


	public void deleteEmployee(Integer employeeId);

	public Employee getEmployee(int employeeid);

	public Employee updateEmployee(Employee employee);

	public Employee searchByEmail(String email);

	public List<Employee> displayByManagerId(String email);

	public List<Holiday> getAllHoliday();

	public void deleteHoliday(Integer Days_id);

	public void addHoliday(Holiday holiday);

	public String getEmployeeName(String email);

}
