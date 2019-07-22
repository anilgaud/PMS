package com.kgate.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:appContext.xml" })
public class TaskServiceTest {

	@Autowired
	TaskService taskservice;

	@Before
	public void setUp() throws Exception {
	}

	/*
	 * @Test public void testtask() { String email="pritis2603@gmail.com";
	 * System.out.println(taskservice.getalltaskdetails(email)); }
	 */

	/*
	 * @Test public void testtask() {
	 * 
	 * int taskid=1;
	 * 
	 * System.out.println(taskservice.getEmployeeTask(taskid)); }
	 */

	/*
	 * @Test public void testtask() { String name="update";
	 * System.out.println(taskservice.getEmployeeName(name));
	 * 
	 * }
	 */

	
	/*
	 * @Test public void testtask() { String name="Kiran Tiwari";
	 * System.out.println(taskservice.EmployeeEmail(name)); }
	 */
	/*
	 * @Test public void testtask() { String email="pawarvihan5@gmail.com";
	 * System.out.println(taskservice.getManagerEmail(email)); }
	 */

	/*
	 * @Test public void testtask() { String email="kiran1010.kt@gmail.com";
	 * System.out.println(taskservice.getManagerName(email)); }
	 */
	/*
	 * @Test check 
	 *  public void testtask() { int id=33;
	 * 
	 * int id=31; System.out.println(taskservice.getProjectName(id)); }
	 */
	
	/*
	 * @Test public void testtask() {
	 * 
	 * int mgrId=30; System.out.println(taskservice.getTaskList(mgrId)); }
	 */
	
	/*
	 * @Test public void testtask() {
	 * 
	 * String email="rajans2206@gmail.com";
	 * System.out.println(taskservice.getEmpNameList(email)); }
	 
	 */
	
	
}
