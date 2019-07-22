package com.kgate.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.TaskDetails;
import com.kgate.service.EmployeeService;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class GetallTask {

	@Autowired
	private TaskService taskservice;
	
	@Test
	public void alltask() {
		List<TaskDetails> l=taskservice.getAllTask();
		
		System.out.println(l);
	}
	
	

}
