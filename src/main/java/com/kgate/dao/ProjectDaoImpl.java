package com.kgate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.kgate.model.Employee;
import com.kgate.model.ProjectDetails;
import com.kgate.model.ProjectReportDTO;

import com.kgate.model.TaskDTO;

import com.sun.javafx.image.impl.IntArgb;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;


    @Override
    public void createProject(ProjectDetails project) {
        Session s = this.sessionFactory.getCurrentSession();
      
        s.saveOrUpdate(project);
     
        
    }


	@Override
	@SuppressWarnings("unchecked")
	public List<ProjectDetails> dispalyProjects() {
		return sessionFactory.getCurrentSession().createQuery("from ProjectDetails").list();
	}

	@Override
	public int getManagerid(String email) {
		Query q = sessionFactory.getCurrentSession()
				.createQuery(" select id from  Employee where email='" + email + "'");

		/*
		 * int id=Integer.parseInt(s); return id;
		 */
		int id = (int) q.uniqueResult();
		return id;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskDTO> displayAllStatus(int id) {

		String query = "select a.name AS 'Employee Name' ,b.name AS 'Manager Name', task_details.task_Name, task_details.task_Type,task_details.tStart_Time,task_details.tEnd_Time, task_details.taskStatus,task_details.tSub_Date from employee_details a, employee_details b cross join task_details where a.category = 'employee' AND a.managerId = b.id AND b.category ='Manager' AND task_details.Emp_Email=a.email AND task_details.projectId='"
				+ id + "'";

		List<TaskDTO> listtsk = new ArrayList<TaskDTO>();
		List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(query).list();

		for (Object d : data) {

			Object arr[] = (Object[]) d;
			String st, st1, st2, st3, st4, st5, st6, st8, st9;

			TaskDTO tdto = new TaskDTO();

			st = (String) arr[0];
			st1 = (String) arr[1];
			st2 = (String) arr[2];
			st3 = (String) arr[3];
			st4 = "";
			try {
				st4 = arr[4].toString();
			} catch (NullPointerException e) {
				System.out.println("nullpoint exception Date:::" + e);
			}

			st5 = "";
			try {
				st5 = arr[5].toString();
			} catch (NullPointerException E) {
				System.out.println("Nullpoint Exception Assigned::" + E);
			}

			st6 = (String) arr[6];
			st9 = (String) arr[7];

			tdto.setEmp_name(st);
			tdto.setName(st1);
			tdto.setTask_Name(st2);
			tdto.setTask_Type(st3);
			tdto.settStartDate(st4);
			tdto.settEndDate(st5);
			tdto.setStatus(st6);
			tdto.setTsubDate(st9);

			listtsk.add(tdto);
		}

		return listtsk;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getProjectByEmail(String email) {

		return sessionFactory.getCurrentSession().createQuery("from ProjectDetails where manageremail='" + email + "'")
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProjectReportDTO> listProjectReport() {
		List<ProjectReportDTO> listproject = new ArrayList<>();
//        listproject = sessionFactory.getCurrentSession().createSQLQuery("select project_details.project_Name, project_details.pstart_Date, project_details.pEnd_Date from project_details").list();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> li = sessionFactory.getCurrentSession().createSQLQuery(
				"select project_details.project_Name, project_details.pstart_Date, project_details.pEnd_Date, project_details.project_id  from project_details")
				.list();

		List<Object> li2 = sessionFactory.getCurrentSession().createSQLQuery(
				"select task_details.task_id, task_details.projectId, task_details.tStart_Time,task_details.tEnd_Time, task_details.tSub_Date,task_details.taskStatus from task_details")
				.list();

		List<Object> li3 = sessionFactory.getCurrentSession().createSQLQuery("select holiday.hDays from holiday ")
				.list();

		System.out.println("Task Details::::tets" + li2);
		System.out.println("");
		String com, var;
		int i, j, k;
		
		for (Object ob : li) {
			ProjectReportDTO dTO = new ProjectReportDTO();
			Object arr[] = (Object[]) ob;
			String s1, s2, s3, s4;
			int totalholiday = 0,task_holiday=0;
			s1 = arr[0].toString();
			s2 = arr[1].toString();
			s3 = arr[2].toString();
			i = (int) arr[3];
			System.out.println("Id:" + i);
			System.out.println("start Date::" + s2);
			System.out.println("start Date::" + s3);
			int flag = 0;
			float daysBetween = 0;
			int count = 0;
			float daysBetween2 = 0;
			float daysBetween3 = 0;
			float daysBetween4 = 0,proexpectdate=0,task_holiday1=0, daysBetween41=0;
			float daysBetween5 = 0;
			
			for (Object ob2 : li2) {
				String ss1, ss2, ss4, ss5;
				String ss3 = null;
				Object arr2[] = (Object[]) ob2;
//                k=(int)arr2
				j = (int) arr2[1];
				ss1 = "";
				try {
					ss1 = arr2[2].toString();
				} catch (Exception e) {
				}
				ss2 = "";
				try {
					ss2 = arr2[3].toString();
				} catch (Exception e) {
				}
				ss5 = "";
				try {
					ss5 = arr2[5].toString();
				} catch (Exception e) {
				}
				try {

					ss3 = arr2[4].toString();
				} catch (NullPointerException ep) {
					System.out.println("Exception:::: " + ep);

				}

				System.out.println("Id2:" + j);

				if (i == j) {
					if (ss3 == null || ss3.isEmpty() || ss5.equals("W .I. P")) {
						dTO.setProStatus("Work in Progress");
						flag++;
					} else if (flag == 0) {
						dTO.setProStatus("Complete");

					}
					if (ss3 == null || ss3.isEmpty()) {
						ss4 = java.time.LocalDate.now().toString();
						try {
							Date dateBefore2 = myFormat.parse(ss4);
							System.out.println("dateBefore2::::   " + dateBefore2);
							Date dateAfter2 = myFormat.parse(ss2);
							System.out.println("DateAfter:::::   " + dateAfter2);
							Date dateStart = myFormat.parse(ss1);
							long difference2 = dateBefore2.getTime() - dateAfter2.getTime();
							long difference3 = dateAfter2.getTime() - dateStart.getTime();
							long difference4 = difference2 + difference3;
							System.out.println("Difference:::::   " + difference2);
							daysBetween3 = (difference2 / (1000 * 60 * 60 * 24));
							daysBetween4 = (difference3 / (1000 * 60 * 60 * 24));
							System.out.println("Days:::" + daysBetween3);
							daysBetween2 = daysBetween2 + daysBetween3 + daysBetween4;
							
							
						} catch (ParseException ex) {
							Logger.getLogger(ProjectDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
						}

						System.out.println("Current Date:" + ss4);
					} else {

						try {
							Date dateBefore2 = myFormat.parse(ss3);
							System.out.println("dateBefore2::::   " + dateBefore2);
							Date dateAfter2 = myFormat.parse(ss2);
							System.out.println("DateAfter:::::   " + dateAfter2);
							Date dateStart = myFormat.parse(ss1);
							System.out.println("Start Date::: " + dateStart);
							long difference2 = dateBefore2.getTime() - dateAfter2.getTime();
							long difference3 = dateAfter2.getTime() - dateStart.getTime();
							long difference4 = difference2 + difference3;
							System.out.println("Difference:::::   " + difference2);
							daysBetween3 = (difference2 / (1000 * 60 * 60 * 24));
							daysBetween4 = (difference3 / (1000 * 60 * 60 * 24));
							System.out.println("Days:::" + daysBetween3);
							System.out.println("Days:::::::" + daysBetween4);
							daysBetween2 = daysBetween2 + daysBetween3 + daysBetween4;
							System.out.println("daysbetween2:::" + daysBetween2);
						} catch (ParseException ex) {
							Logger.getLogger(ProjectDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
						}

					}
				/////task holiday
					for (Object h : li3) {
						
						/*Object arr3[] = (Object[]) h;
						String hday = arr3[0].toString();*/
						String hday=h.toString();
						try {
							int dif = (int) myFormat.parse(hday).getTime();
							System.out.println("hoanil1 "+dif);
							int  dif1= (int)myFormat.parse(ss1).getTime();
							int dif2=(int) myFormat.parse(ss2).getTime();
							System.out.println("hoanil2 "+dif1);
							System.out.println("hoanil3 "+dif2);
							if (dif>dif1 && dif <dif2 ) {
								task_holiday = task_holiday + 1;
								
							}
							
						} catch (Exception e) {
							
						}
					}
					//project duration calaculation AG
					
					
					proexpectdate=proexpectdate+daysBetween4;
				}
		
				
				
				

			}
//            daysBetween2 = daysBetween2 + daysBetween3;
			int days = (int) daysBetween2;
			String s5 = Integer.toString(days);
			System.out.println("Days Between2::::   " + s5);
			try {
				Date dateBefore = myFormat.parse(s2);
				System.out.println("dateBefore::::   " + dateBefore);
				Date dateAfter = myFormat.parse(s3);
				System.out.println("DateAfter:::::   " + dateAfter);
				long difference = dateAfter.getTime() - dateBefore.getTime();
				System.out.println("Difference:::::   " + difference);
				daysBetween = (difference / (1000 * 60 * 60 * 24));
				System.out.println("Days:::::    " + daysBetween);

			} catch (ParseException ex) {
				Logger.getLogger(ProjectDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
			int days3 = (int) daysBetween;
			s4 = Integer.toString(days3);
			daysBetween4 = daysBetween2 - daysBetween;
			int days2 = (int) daysBetween4;
			String s6 = Integer.toString(days2);

			SimpleDateFormat myFormat1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd");
			String startdate = "", enddate = "";
			try {
				startdate = myFormat1.format(current.parse(s2));
				enddate = myFormat1.format(current.parse(s3));
			} catch (Exception e) {
				// TODO: handle exception
			}

			
			/*for (int h = 0; i < li3.size(); i++) {
				String temp = (String) li3.get(h);
				System.out.println("holiday Date:::" + temp);
			}*/
			for (Object h : li3) {
				
				/*Object arr3[] = (Object[]) h;
				String hday = arr3[0].toString();*/
				String hday=h.toString();
				try {
					int dif = (int) myFormat.parse(hday).getTime();
					System.out.println("hoanil1 "+dif);
					int  dif1= (int)myFormat.parse(s2).getTime();
					int dif2=(int) myFormat.parse(s3).getTime();
					System.out.println("hoanil2 "+dif1);
					System.out.println("hoanil3 "+dif2);
					if (dif>dif1 && dif <dif2 ) {
						totalholiday = totalholiday + 1;
					}
					
				} catch (Exception e) {
					
				}
			}
			System.out.println("hoanil "+totalholiday);
		    Integer tholiday = days3 - totalholiday;
			String totalholidayexpectdate = tholiday.toString();
			
			Integer taskholiday=days-task_holiday;
			String tsholiday=taskholiday.toString();
			
			/*Integer deviation=taskholiday-tholiday;*/
			
			//project duration using taskholiday
			Integer psd=(int) (proexpectdate-task_holiday);
			String psd1=psd.toString();
			
			Integer deviation=taskholiday-psd;
			dTO.setProject_name(s1);
			dTO.setpStartDate(startdate); // s2
			dTO.setpEndDate(enddate); // s3
			dTO.setExpectDate(psd1); // s4   //totalholidayexpectdate
			dTO.setCompleteTime(tsholiday);                //s5
			dTO.setVariationTime(deviation.toString());     //s6
			System.out.println("Name:     " + s1);
			System.out.println("Start:     " + s2);
			System.out.println("End:     " + s3);
			listproject.add(dTO);
		}

		return listproject;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskDTO> displayAllStatus2(String email, int id) {

		String query = "select a.name AS 'Employee Name' , td.task_Name, td.task_Type,td.tStart_Time,td.tEnd_Time, td.taskStatus,td.tSub_Date,datediff(td.tEnd_Time,td.tStart_Time)  as EstimateDays , if(td.tSub_Date is null,datediff(curdate(),td.tStart_Time),datediff(td.tSub_Date,td.tStart_Time)) as ActualDays from employee_details a, employee_details b cross join task_details td where a.category = 'employee' AND a.managerId = b.id AND b.category ='Manager' AND td.Emp_Email=a.email AND b.email='"
				+ email + "' AND td.projectId='" + id + "'";
		
		List<Object> li3 = sessionFactory.getCurrentSession().createSQLQuery("select holiday.hDays from holiday ")
				.list();
		

		List<TaskDTO> listtsk = new ArrayList<TaskDTO>();
		List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(query).list();

		for (Object d : data) {

			Object arr[] = (Object[]) d;
			String st, st2, st3, st4, st5, st6, st9, st10, st11, st12;

			TaskDTO tdto = new TaskDTO();

			st = (String) arr[0];
			st2 = (String) arr[1];
			st3 = (String) arr[2].toString();
			st4 = "";
			try {
				st4 = arr[3].toString();
			} catch (NullPointerException e) {
				System.out.println("nullpoint exception Date:::" + e);
			}

			st5 = "";
			try {
				st5 = arr[4].toString();
			} catch (NullPointerException E) {
				System.out.println("Nullpoint Exception Assigned::" + E);
			}

			st6 = "";
			try {
				st6 = (String) arr[5].toString();
			} catch (NullPointerException e) {
				System.out.println("NUllpoint Exception:::: " + e);
			}
			System.out.println("Task Status::: " + st6);
			st9 = "";
			try {
				st9 = (String) arr[6].toString();
			} catch (NullPointerException e) {
				System.out.println("Nullpoint exception::" + e);
			}

			st10 = "";
			try {
				st10 = (String) arr[7].toString();
			} catch (NullPointerException e) {
				System.out.println("Nullpoint Exception" + e);
			}
			st11 = "";
			try {
				st11 = (String) arr[8].toString();
			} catch (NullPointerException e) {
				System.out.println("Null Point Exception   " + e);
			}

			int est = 0;
			try {
				est = Integer.parseInt(st10);
			} catch (Exception e) {

			}
			int act = 0;
			try {
				act = Integer.parseInt(st11);
			} catch (Exception e) {
			}
//            System.out.println("estimated:::" + est);

//            System.out.println("estimated:::" + act);
			int delay = 0;
			try {
				delay = act - est;
			} catch (Exception e) {
			}
			// System.out.println("estimated:::" + delay);
			// System.out.println("Diff:::" + delay);
			st12 = "";
			try {
				st12 = Integer.toString(delay);
			} catch (Exception e) {
			}
			
			//////////calculate holiday
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			int totalholiday=0;
             for (Object h : li3) {
				
				/*Object arr3[] = (Object[]) h;
				String hday = arr3[0].toString();*/
				String hday=h.toString();
				try {
					int dif = (int) myFormat.parse(hday).getTime();
					System.out.println("hoanil1 "+dif);
					int  dif1= (int)myFormat.parse(st4).getTime();
					int dif2=(int) myFormat.parse(st5).getTime();
					System.out.println("hoanil2 "+dif1);
					System.out.println("hoanil3 "+dif2);
					if (dif>dif1 && dif <dif2 ) {
						totalholiday = totalholiday + 1;
					}
					
				} catch (Exception e) {
					
				}
			}

             Integer estimatedays=est-totalholiday;
             String estdate=estimatedays.toString();
             
             Integer actualdays=act-totalholiday;
             String actuald=actualdays.toString();
             
             Integer Delay=actualdays-estimatedays;
             String delay1=Delay.toString();
             
             //fomate of dates
             SimpleDateFormat myFormat1 = new SimpleDateFormat("dd/MM/yyyy");
 			SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd");
 			String startdate = "", enddate = "",submitdate="";
 			try {
 				startdate = myFormat1.format(current.parse(st4));
 				enddate = myFormat1.format(current.parse(st5));
 				submitdate= myFormat1.format(current.parse(st9));
 			} catch (Exception e) {
 				// TODO: handle exception
 			}

             
			tdto.setEmp_name(st);
			tdto.setTask_Name(st2);
			tdto.setTask_Type(st3);
			tdto.settStartDate(startdate); //st4
			tdto.settEndDate(enddate);  //st5
			tdto.setStatus(st6);   
			tdto.setTsubDate(submitdate);     //st9
			tdto.setEstimateDays(estdate);  //st10
			tdto.setActualDays(actuald);       //st11
			tdto.setDelayDays(delay1);         //st12

			listtsk.add(tdto);
		}

		return listtsk;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String displayProjectName(int id) {
		String query = "select project_Name from ProjectDetails where project_id='" + id + "'";
		Query query2 = sessionFactory.getCurrentSession().createQuery(query);
		String name = (String) query2.uniqueResult();
		return name;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskDTO> displayAllStatus3(String email, int id) {

//        String query = "select a.name AS 'Employee Name' , td.task_Name, td.task_Type,td.tStart_Time,td.tEnd_Time, td.taskStatus,td.tSub_Date,datediff(td.tEnd_Time,td.tStart_Time)  as EstimateDays , if(td.tSub_Date is not null,datediff(curdate(),td.tStart_Time),datediff(td.tSub_Date,td.tStart_Time)) as ActualDays, a.id from employee_details a, employee_details b cross join task_details td where a.category = 'employee' AND a.managerId = b.id AND b.category ='Manager' AND td.Emp_Email=a.email AND b.email='" + email + "' AND td.projectId='" + id + "' order by a.id";
		String query = "select a.name AS 'Employee Name' , td.task_Name, td.task_Type,td.tStart_Time,td.tEnd_Time, td.taskStatus,td.tSub_Date,datediff(td.tEnd_Time,td.tStart_Time)  as EstimateDays , if(td.tSub_Date is null,datediff(curdate(),td.tStart_Time),datediff(td.tSub_Date,td.tStart_Time)) as ActualDays, a.id from employee_details a, employee_details b cross join task_details td where a.category = 'employee' AND a.managerId = b.id AND b.category ='Manager' AND td.Emp_Email=a.email AND b.email='"
				+ email + "' AND td.projectId='" + id + "' order by a.id";
		List<TaskDTO> listtsk = new ArrayList<TaskDTO>();
		List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(query).list();
		
		List<Object> li3 = sessionFactory.getCurrentSession().createSQLQuery("select holiday.hDays from holiday ")
				.list();
		
		int x = 0;
		for (int i = 0; i < data.size(); i++) {
			int totalholiday=0;
			TaskDTO tdo = new TaskDTO();
			Object arr[] = (Object[]) data.get(i);
			String st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12;
			int empid;
//            int flag = 0;

			st1 = (String) arr[0].toString();
			st2 = (String) arr[1].toString();
			st3 = (String) arr[2].toString();
			st4 = "";
			try {
				st4 = (String) arr[3].toString();
			} catch (Exception e) {
			}
			st5 = "";
			try {
				st5 = (String) arr[4].toString();
			} catch (Exception e) {
			}
			st6 = "";
			try {
				st6 = (String) arr[5].toString();
			} catch (Exception e) {
			}
			st7 = "";
			try {
				st7 = (String) arr[6].toString();
			} catch (NullPointerException e) {

			}

			st8 = "";
			try {
				st8 = (String) arr[7].toString();
			} catch (Exception e) {
			}
			st9 = "";
			try {
				st9 = (String) arr[8].toString();
			} catch (Exception e) {
			}
			int flag = 0;
			empid = Integer.parseInt((String) arr[9].toString());
			if (x == empid) {
				flag = 1;
			}
			int est = 0;
			try {
				est = Integer.parseInt(st8);

			} catch (Exception e) {
			}
			int act = 0;
			try {
				act = Integer.parseInt(st9);
			} catch (Exception e) {
			}
			int est1;
			int delay = 0;
			try {
				delay = act - est;
			} catch (Exception e) {
			}
			System.out.println("Estimate time in Loop1::" + est);
			for (int j = i + 1; j < data.size(); j++) {
				Object arr2[] = (Object[]) data.get(j);

				String sst1, sst2, sst3, sst4, sst5, sst6, sst7, sst8, sst9, sst10;
				int empid2, act2;
				sst1 = (String) arr2[0].toString();
				sst2 = (String) arr2[1].toString();
				sst3 = (String) arr2[2].toString();
				sst4 = "";
				try {
					sst4 = (String) arr2[3].toString();
				} catch (Exception e) {
					System.out.println("Null point Exception" + sst4);
				}
				sst5 = "";
				try {
					sst5 = (String) arr2[4].toString();
				} catch (Exception e) {
				}
				sst6 = "";
				try {
					sst6 = (String) arr2[5].toString();
				} catch (Exception e) {
				}
				sst7 = "";
				try {
					sst7 = (String) arr2[6].toString();
				} catch (NullPointerException e) {
					System.out.println("Nullpoint exception: " + e);
				}
				sst8 = "";
				try {
					sst8 = (String) arr2[7].toString();
				} catch (Exception e) {
				}
				sst9 = "";
				try {
					sst9 = (String) arr2[8].toString();
				} catch (Exception e) {
				}
				empid2 = 0;
				try {
					empid2 = Integer.parseInt((String) arr2[9].toString());
				} catch (Exception e) {
				}
				est1 = 0;
				try {
					est1 = Integer.parseInt(sst8);
				} catch (Exception e) {
				}
				act2 = 0;
				try {
					act2 = Integer.parseInt(sst9);
				} catch (Exception e) {
				}
				int delay2 = 0;
				try {
					delay2 = act2 - est1;
				} catch (Exception e) {
				}
				System.out.println("second LOOP::" + est1);
				int act1 = 0;
				try {
					act1 = Integer.parseInt(sst9);
				} catch (Exception e) {
				}
				if (flag == 0) {
					if (empid == empid2) {
						est = est + est1;
						act = act + act2;
						delay = delay + delay2;
//                        System.out.println("Estimate time in Loop2::" + est);
//                        sst10 = Integer.toString(est);
//                        tdo.setEstimateDays(sst10);
						
				//////////calculate holiday
						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				
			             for (Object h : li3) {
							
							/*Object arr3[] = (Object[]) h;
							String hday = arr3[0].toString();*/
							String hday=h.toString();
							try {
								int dif = (int) myFormat.parse(hday).getTime();
								System.out.println("hoanil1 "+dif);
								int  dif1= (int)myFormat.parse(sst4).getTime();
								int dif2=(int) myFormat.parse(sst5).getTime();
								System.out.println("hoanil2 "+dif1);
								System.out.println("hoanil3 "+dif2);
								if (dif>dif1 && dif <dif2 ) {
									totalholiday = totalholiday + 1;
								}
								
							} catch (Exception e) {
								
							}
						}

					}
				}
			}
			
			//////////calculate holiday
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			
             for (Object h : li3) {
				
				/*Object arr3[] = (Object[]) h;
				String hday = arr3[0].toString();*/
				String hday=h.toString();
				try {
					int dif = (int) myFormat.parse(hday).getTime();
					System.out.println("hoanil1 "+dif);
					int  dif1= (int)myFormat.parse(st4).getTime();
					int dif2=(int) myFormat.parse(st5).getTime();
					System.out.println("hoanil2 "+dif1);
					System.out.println("hoanil3 "+dif2);
					if (dif>dif1 && dif <dif2 ) {
						totalholiday = totalholiday + 1;
					}
					
				} catch (Exception e) {
					
				}
			}
             
             Integer th=est-totalholiday;
             String th1=th.toString();
             
			st10 = Integer.toString(est);
			System.out.println("Estimate time in Loop2nd::" + st10);
			st11 = Integer.toString(act);
			
			Integer thh=act-totalholiday;
			String thh1=thh.toString();
			
			st12 = Integer.toString(delay);
			
			Integer thhh=thh-th;
			String thhh1=thhh.toString();
			
			tdo.setEmp_name(st1);
			tdo.setTask_Name(st2);
			tdo.setTask_Type(st3);
			tdo.settStartDate(st4);
			tdo.settEndDate(st5);
			tdo.setStatus(st6);
			tdo.setTsubDate(st7);
			tdo.setEstimateDays(th1);   //st10
			tdo.setActualDays(thh1);    //st11  
			tdo.setDelayDays(thhh1);     //st12
			if (flag == 0) {
				listtsk.add(tdo);
			}
			x = empid;
		}
		return listtsk;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getmanagernameformail(String email) {

		String query = "select name from Employee where email='" + email + "'";
		Query query2 = sessionFactory.getCurrentSession().createQuery(query);
		String name = (String) query2.uniqueResult();
		return name;


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getProjectDates(int projectId) {
		return sessionFactory.getCurrentSession()
				.createQuery("from ProjectDetails where project_id='" + projectId + "'").list();
	}

 

	@Override
	public String findproject(String projectname) {
		 String query1 = "select project_Name from project_details where project_Name='" + projectname + "'";
	        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query1);
	        String name1 = (String) query2.uniqueResult();
	        return name1;
		
	}

	@Override
	public void deleteproject(int id) {
		  Session s = this.sessionFactory.getCurrentSession();
	        ProjectDetails p =(ProjectDetails) s.load(ProjectDetails.class, new Integer(id));
	        if (p != null) {
	            s.delete(p);
	        } 

		
	}

	@Override
	public ProjectDetails getProjectById(int id) {
		 return (ProjectDetails) sessionFactory.getCurrentSession().get(
				 ProjectDetails.class,
	                id);
	}

	

}
