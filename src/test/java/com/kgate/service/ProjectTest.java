package com.kgate.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
public class ProjectTest {

	@Autowired ProjectService projectservice;
	
	/*@Test
	public void test() {
		System.out.println(projectservice.findproject("employee123"));
		System.out.println(projectservice.findproject("employeeSystem1234"));
	}
*/
	
	/*@Test
	public void test() {
		System.out.println(projectservice.getmanagernameformail("pritis2603@gmail.com"));
		System.out.println(projectservice.getmanagernameformail("pr@gmail.com"));
	}
	*/
	
	/*@Test
	public void test() {
		System.out.println(projectservice.dispalyProjects());
	}*/
	
/*	@Test
	public void test() {
		System.out.println(projectservice.displayAllStatus(4));
	}*/
	
	/*@Test
	public void test() {
		System.out.println(projectservice.displayAllStatus2("priti.v2technologies@gmail.com", 1));
	}*/
	
	/*@Test
	public void test() {
		System.out.println(projectservice.displayAllStatus3("priti.v2technologies@gmail.com", 1));
	}*/
	
	/*@Test
	public void test() {
		System.out.println(projectservice.getProjectByEmail("pritis2603@gmail.com"));
	}
	*/
	/*@Test
	public void test() {
		System.out.println(projectservice.getProjectById(3));
	}*/
	
	
}
