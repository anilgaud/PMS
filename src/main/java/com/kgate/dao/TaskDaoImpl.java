package com.kgate.dao;

import java.util.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import com.kgate.model.Employee;
import com.kgate.model.TaskDTO;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kgate.model.ProjectDetails;
import com.kgate.model.TaskDetails;
import java.util.ArrayList;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTask(TaskDetails task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    /*
	 * @Override private void deleteTask(TaskDetails task) { Session s =
	 * this.sessionFactory.getCurrentSession(); TaskDetails task = (TaskDetails)
	 * s.load(TaskDetails.class, new Integer(task_id)); if (null != task) {
	 * this.sessionFactory.getCurrentSession().delete(task); } }
     */
    @Override
    public void deleteTask(int task_id) {
        TaskDetails task = (TaskDetails) sessionFactory.getCurrentSession().load(TaskDetails.class, task_id);
        if (null != task) {
            this.sessionFactory.getCurrentSession().delete(task);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskDetails> getAllTask() {
        return sessionFactory.getCurrentSession().createQuery("from TaskDetails").list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskDetails> getByProjectId(int id) {

        return sessionFactory.getCurrentSession().createQuery("from TaskDetails where projectId='" + id + "'").list();

    }

    // return employee name from manager email
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getEmpNameList(String email) {

        String query2 = "select name from employee_details where category='employee' and managerid = (select id from employee_details where category = 'manager' and email='"
                + email + "')";
        return sessionFactory.getCurrentSession().createSQLQuery(query2).list();

    }

    @Override
    public TaskDetails getTask(int task_id) {

        return (TaskDetails) sessionFactory.getCurrentSession().get(TaskDetails.class, task_id);
    }

    @SuppressWarnings("unchecked")
    public List<TaskDetails> getalltaskdetails(String email) {
        return sessionFactory.getCurrentSession().createQuery("from TaskDetails where Emp_Email='" + email + "'")
                .list();
    }

    @Override
    public void updateTask(TaskDetails taskDetails) {

        sessionFactory.getCurrentSession().saveOrUpdate(taskDetails);
    }

    @Override
    public void updatetask1(String date, String email, int tid, String st) {
        Query query = sessionFactory.getCurrentSession().createQuery("update TaskDetails set tSub_Date='" + date
                + "',taskStatus='" + st + "' where Emp_Email='" + email + "' and task_id='" + tid + "'");
        query.executeUpdate();

    }

    @Override
    public TaskDetails getEmployeeTask(int taskid) {
        return (TaskDetails) sessionFactory.getCurrentSession().get(TaskDetails.class, taskid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TaskDetails> getTaskList(int mgrId) {
        return sessionFactory.getCurrentSession().createQuery("from TaskDetails where projectId='" + mgrId + "'")
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TaskDTO> getEmpTasklist(String email) {
        String query = "select project_details.project_Name, task_details.task_Type, task_details.task_Name, task_details.tStart_Time, task_details.tEnd_Time,employee_details.name, task_details.taskStatus, task_details.task_id, task_details.Emp_Email  from task_details cross join employee_details, project_details where task_details.projectId = project_details.project_id and task_details.managerId=employee_details.id and task_details.Emp_Email ='"
                + email + "'";
        List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(query).list();
        System.out.println("List of Object::::   " + data);

        List<TaskDTO> listdto = new ArrayList<TaskDTO>();
        List<Object> testObj = new ArrayList<Object>();
        for (Object d : data) {

            Object arr[] = (Object[]) d;
            String st, st1, st2, st3, st4, st5, st6, st8;
            int i;

            TaskDTO tdto = new TaskDTO();

            st = (String) arr[0];
            st1 = (String) arr[1];
            st2 = (String) arr[2];
            st5 = (String) arr[5];
            st3 = "";
            try {
                st3 = (String) arr[3].toString();
            } catch (Exception e) {
            }
            st4 = "";
            try {
                st4 = (String) arr[4].toString();
            } catch (Exception e) {
            }
            st6 = (String) arr[6];
            i = (int) arr[7];
            st8 = (String) arr[8];

            tdto.setProject_Name(st);
            tdto.setTask_Type(st1);
            tdto.setTask_Name(st2);
            tdto.settStartDate(st3);
            tdto.settEndDate(st4);
            tdto.setName(st5);
            tdto.setStatus(st6);
            tdto.setId(i);
            tdto.setEmail(st8);

            listdto.add(tdto);
            System.out.println("List of Name::::   " + tdto);
            System.out.println("List of Object::::   " + listdto);

        }
        return listdto;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getManagerEmail(String email) {
        String query = "select distinct(e.email) from task_details td cross join employee_details e where td.managerId = e.id and td.Emp_Email='"
                + email + "'";

        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query);
        String name = (String) query2.uniqueResult();
        return name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getEmployeeName(String name) {
        String query = "select distinct(employee_details.name) from employee_details cross join task_details where employee_details.email='"
                + name + "'";

        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query);
        String name1 = (String) query2.uniqueResult();
        /*String name1 = (String) query2.getFirstResult().toString();*/
        return name1;
    }

    @Override
    public String getProjectName(int id) {
        String query = "select distinct(project_details.project_Name) from project_details cross join task_details where project_details.project_id=task_details.projectId and task_details.task_id='"
                + id + "'";
        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query);
        String name1 = (String) query2.uniqueResult();
        return name1;
    }

    @Override
    public String getManagerName(String name) {
        String query = "select distinct(employee_details.name) from employee_details cross join task_details where task_details.managerId=employee_details.id and task_details.Emp_Email='"
                + name + "'";

        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query);
        String name1 = (String) query2.uniqueResult();
        return name1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String EmployeeEmail(String name) {
        String query = "select email from employee_details where name ='" + name + "'";
        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query);
        String name1 = (String) query2.uniqueResult();
        return name1;
    }

}
