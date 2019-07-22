import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.ProjectDetails;
import com.kgate.service.ProjectService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})

public class CreateProject {
	
	@Autowired
	ProjectService projectservice;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProject() {
		ProjectDetails project=null;
		String project_Name = "EmployeeManagementSystem";
		String project_desc = "ProjectManagementSystem";
		@SuppressWarnings("deprecation")
		Date d1=new Date(2018, 12,19);
		Date pstart_Date=d1;
		Date d2= new Date(2018,12,21);
		Date pEnd_Date=d2;
		
		
		project=new ProjectDetails(project_Name,project_desc,pstart_Date,pEnd_Date);
		projectservice.createProject(project);
		System.out.println(project);
	}
	
	
	/*@Test
	public void testProject() {
		ProjectDetails project=null;
		System.out.println(projectservice.dispalyProjects());
	
	}*/
	
	/*@Test
	public void testProject() {
		System.out.println(projectservice.getManagerid("rajans2206@gmail.com"));
		
		System.out.println(projectservice.getManagerid("priti@gmail.com"));
	}
	*/
}
		

	
	

