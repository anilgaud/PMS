import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.TaskDetails;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class taskAjax {
	
	 @Autowired
	    private TaskService taskservice;
	 @Test
	 public void get() {
		 
		 TaskDetails td=taskservice.getTask(65);
			if(td.gettStart_Time()==null)
			{
				System.out.println("11111");
			}
			else
			{
				System.out.println("00000");
			}
		 
	 }

}
