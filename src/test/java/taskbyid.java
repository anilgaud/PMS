import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.TaskDetails;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class taskbyid {
	
	  @Autowired
	    private TaskService taskservice;

	@Test
	public void  get() {
		
		List<TaskDetails> tl=taskservice.getByProjectId(1);
    	for(int i=0;i<tl.size();i++)
    	{
    		TaskDetails  s1=tl.get(i);
            String p=s1.getTaskStatus();
            
            System.out.println(p);
            if(p.equals("W .I. P"))
            {
            	
            }
           
    	}
	}
}
