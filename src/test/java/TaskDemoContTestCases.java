import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.ProjectDetails;
import com.kgate.model.TaskDetails;
import com.kgate.service.ProjectService;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})


public class TaskDemoContTestCases {
	
	@Autowired
	TaskService taskservice;
	
	/*@Autowired
	ProjectService projectservice;*/

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testtaskdemocont() {
		
		TaskDetails task1=null;
		
		ProjectDetails project=null;
		
		
		String task_Type="integration";
		String task_Name="update";
		String status="Assigned";
	
		@SuppressWarnings("deprecation")
		Date d1=new Date(2018,12,19);
		Date tStart_Time=d1;
		@SuppressWarnings("deprecation")
		Date d2=new Date(2018,12,14);
		Date tEnd_Time=d2;
		
		String Emp_Email="pawarvihan05@gmail.com";
		
		
		 task1=new TaskDetails(task_Type,task_Name,status,tStart_Time,tEnd_Time,Emp_Email);
		
		 System.out.println(task1);
		
	}
	/*
	@Test
	public void testtaskdemocont() {
		TaskDetails task1=null;
		System.out.println(taskservice.getEmployeeEmail("pawarvihan05@gmail.com"));
		
	}*/
	
	
	/*@Test
	public void testtaskdemocont() {
		TaskDetails task1=null;
		String status="Assigned";
		String taskStatus="W.I.P";
		int managerId=8;
		int projectId=1;
		
		System.out.println(taskservice.getByProjectId(projectId));

			
	}*/
				
}
