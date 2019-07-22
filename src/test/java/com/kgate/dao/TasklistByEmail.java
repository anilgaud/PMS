package com.kgate.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.TaskDTO;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class TasklistByEmail {
	
	@Autowired
	TaskService taskservice;
	
	@Test
	public void GetTasklist()
	{
		List<TaskDTO> l=taskservice.getEmpTasklist("a@gmail.com");
		System.out.println(l);
		
	}

}
