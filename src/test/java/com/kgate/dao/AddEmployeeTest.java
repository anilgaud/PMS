package com.kgate.dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kgate.model.Employee;
import com.kgate.service.EmployeeService;
import com.kgate.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})

public class AddEmployeeTest {

    @Autowired
    EmployeeService employeeservice;
    
    @Autowired
    TaskService taskservice;

    /*@Test
    public void testMeth() {
        Employee e = null;
        //	List<Employee> e=new ArrayList<Employee>();
        String name = "rajan singh";
        String telephone = "483674567";
        String adhar = "786sdcj765789";
        String address = "ndnmcsmcls";
        String category = "Employee";
        String email = "rajans2206@gmail.com";
        int managerid = 2;
        String otp = "6543";
        String pan = "pkojkl7867";
        String password = "rajan123";
        String status = "approved";

        Employee e1 = new Employee(otp, name, email, address, telephone, category, status, password, managerid, adhar, pan);

        employeeservice.addEmployee(e1);
        
        

    }*/
    
   /* @Test Check Once
    public void testMeth() {
        
        System.out.println(employeeservice.searchByEmail("pawarvihan05@gmail.com"));
    	
    }  
    
    */
    /*@Test Check Once
    public void testMeth() {
    	Employee e = null;
    	int id=2;
    	e=employeeservice.getEmployee(id);
    	System.out.println(e);
    	
    }
    */
   /* @Test Check Once
    public void testMeth() {
    	Employee e = null;
    	employeeId=
    	 System.out.println(employeeservice.deleteEmployee(employeeId);
     }*/
   /* @Test
    public void testMeth() {
    	Employee e = null;
    	System.out.println(employeeservice.displayByManagerId("rajans2206@gmail.com"));
    }
    	*/
    
   /* @Test
    public void testMeth() {
    	Employee e = null;
    	System.out.println(employeeservice.getAllEmployees());
    }*/
    
    /*@Test
    public void testMeth() {
    	Employee e = null;
    	String skill="Java";
    	System.out.println(employeeservice.searchEmployeesBySkill(skill));
    }*/
   /* @Test Check
    public void testMeth() {
    	Employee e = null;
    	
    	System.out.println(employeeservice.updateEmployee(employee));
    }
    */
    
    
    @Test
    public void testMeth() {
    	int empid=2;
    	System.out.println(employeeservice.getEmployee(empid));
    }
    
   
}





