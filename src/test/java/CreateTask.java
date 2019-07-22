import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.TaskDetails;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })

public class CreateTask {

	@Autowired
	TaskService taskservice;

	@Before
	public void setUp() throws Exception {
	}

	
	  @Test
	  public void testtask() {
	  TaskDetails task = null;
	  String task_Type = "add";
	  String task_Name ="addintegrate";
	  String status = "NotAssigned";
	  task = new TaskDetails(task_Type, task_Name, status);
	  taskservice.addTask(task);
	  System.out.println(task);
	  
	  }
	 

	/*
	 * @Test public void testtask() {
	 * 
	 * TaskDetails task=null;
	 *
	 * System.out.println(taskservice.getAllTask());
	 * 
	 * }
	 */

	
	/*
	 * @Test public void testtask() {
	 * 
	 *  TaskDetails task=null;
	 * 
	 * int task_id=37;
	 * 
	 * int task_id=50;
	 * 
	 * System.out.println(taskservice.getTask(task_id));
	 * 
	 * 
	 * }
	 */
	 

	/*
	 * @Test public void testtask() { 
	 * 
	 * TaskDetails task=null;
	 * 
	 * int id=3;
	 * 
	 * System.out.println(taskservice.getByProjectId(id));
	 * 
	 *  }
	 */

	
	/*
	 * @Test public void testtask() {
	 * 
	 * System.out.println(taskservice.getEmployeeEmail("pawarvihan05@gmail.com"));
	 * 
	 * }
	 */
	 
	
	
	
	
	
	
}
	
	





