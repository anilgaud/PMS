package com.kgate.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.Employee;
import com.kgate.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class EmployeeServiceTest 
{
	@Autowired
	private EmployeeService employeeservice;
	
	@Test
	public void testemployeecheck()
	{
      Employee e=employeeservice.getEmployee(2);
      
      assertEquals("successfully fetched a employee","Manager", e.getCategory());
	}

}
