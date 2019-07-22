package com.kgate.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kgate.model.Employee;
import com.kgate.model.Holiday;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
        // sessionFactory.getCurrentSession().saveOrUpdate(skill);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> searchEmployeesBySkill(String txt) {

        String qry = "select employee0_.name, employee0_.email, employee0_.address, employee0_.telephone, employee0_.category  from  employee_details employee0_ cross  join join_employee_skill listskill1_, employee_skill skill2_  where  employee0_.id=listskill1_.id and listskill1_.skill_Id=skill2_.skill_Id  and skill2_.skill_name LIKE '" + txt + "'";
        List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(qry).list();
        List<Employee> emp = new ArrayList<>();
//      Employee e = new Employee();

        for (Object d : data) {

            Object arr[] = (Object[]) d;
            String st, st1, st2, st3, st4;
//          String s[] = null;
//          for (int i = 0; i <= arr.length; i++) {
//              s[i] = (String) arr[i];
//
//          }
            Employee e = new Employee();
            st = (String) arr[0];
            st1 = (String) arr[1];
            st2 = (String) arr[2];
            st3 = (String) arr[3];
            st4 = (String) arr[4];

            e.setName(st);
            e.setEmail(st1);
            e.setAddress(st2);
            e.setTelephone(st3);
            e.setCategory(st4);
            emp.add(e);

        }

        return emp;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> searchEmployees(String txt) {

        String query = "from Employee u where u.name like '" + txt + "%' or u.email like '" + txt
                + "%' or u.address like '" + txt + "%' or u.telephone like '" + txt + "%' ";
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees() {

        return sessionFactory.getCurrentSession().createQuery("from Employee")
                .list();
    }

    @Override
    public void deleteEmployee(Integer employeeId) {

        String query = "delete employee_details, join_employee_skill from employee_details inner join  join_employee_skill  where  employee_details.id = join_employee_skill.id and employee_details.id =" + employeeId + "";

        SQLQuery sqlq = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlq.executeUpdate();

    }

    public Employee getEmployee(int empid) {
        return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empid);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
        return employee;
    }

    @Override
    public Employee searchByEmail(String email) {
        String query = "from Employee u where u.email = '" + email + "'";
        List<Object> emps = sessionFactory.getCurrentSession().createQuery(query).list();
        if (emps != null && emps.size() > 0) {
            Employee employee = (Employee) emps.get(0);
            return employee;
        }
        return null;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> displayByManagerId(String email) {

        String query = "select  name,address,telephone,email,status,managerId,password,category from employee_details where managerId=(select id from employee_details where email='" + email + "') ";
//       String query = "select  name,address,telephone,email,status,managerId,otp,password,category from employee_details   where category='Employee' and managerId=(select ManagerId from employee_details where category='Manager' and email='"+email+"')";
        //        String query = "select  name,address,telephone,email,status,managerId,otp,password,category from employee_details where managerId=(select id from employee_details where email='" + email + "') ";

        List<Object> data = sessionFactory.getCurrentSession().createSQLQuery(query).list();
        List<Employee> emp = new ArrayList<>();
        for (Object d : data) {

            Object arr[] = (Object[]) d;
            String st, st1, st2, st3, st4;

            Employee e = new Employee();
            st = (String) arr[0];
            st1 = (String) arr[1];
            st2 = (String) arr[2];
            st3 = (String) arr[3];
            st4 = (String) arr[4];

            e.setName(st);
            e.setAddress(st1);
            e.setTelephone(st2);
            e.setEmail(st3);
            e.setCategory(st4);
            emp.add(e);
        }
        return emp;
    }

    @Override
    public void addHoliday(Holiday holiday) {
        sessionFactory.getCurrentSession().save(holiday);

    }

    @Override
    public List<Holiday> getAllHoliday() {
        return sessionFactory.getCurrentSession().createQuery("from Holiday").list();
    }

    @Override
    public void deleteHoliday(Integer Days_id) {
        Holiday holiday = (Holiday) sessionFactory.getCurrentSession().load(Holiday.class, Days_id);
        if (null != holiday) {
            this.sessionFactory.getCurrentSession().delete(holiday);
        }

    }

    @Override
    public String getEmployeeName(String email) {

        String query1 = "select name from employee_details where email='" + email + "'";
        Query query2 = sessionFactory.getCurrentSession().createSQLQuery(query1);
        String name1 = (String) query2.uniqueResult();
        return name1;

    }

    private static final int limitResultsPerPage = 3;

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> getAllEmployees(Integer page) {
        page = page - 1;
        Query q = sessionFactory.getCurrentSession().createQuery("from Employee");
        q.setFirstResult((int) (page * limitResultsPerPage));
        q.setFetchSize(limitResultsPerPage);
        q.setMaxResults(limitResultsPerPage);
        return (List<Employee>) q.list();

    }

}
