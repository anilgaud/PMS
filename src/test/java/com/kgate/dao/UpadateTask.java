package com.kgate.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.service.TaskService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class UpadateTask {
	
	@Autowired
	TaskService taskservice;
	
	@Test
	public void Ud() {
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String s=sdf.format(d);
		taskservice.updatetask1(s, "a@gmail.com", 7, "complete");
	}

}
