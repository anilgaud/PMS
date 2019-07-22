import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.service.LoginService2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
public class LoginTest {
	
	@Autowired
	LoginService2 loginservice2;

	@Test
	public void test() {
		
		boolean flag= loginservice2.checkLogin("pritis2603@gmail.com", "1234","Admin");

		System.out.println(flag);
	}
}


