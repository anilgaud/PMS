package com.kgate.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("LoginDao2")
public class LoginDao2Impl implements LoginDao2  {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginDao2Impl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 public void setSessionFactory(SessionFactory sf) {
			this.sessionFactory = sf;
		}

	 protected Session getSession()
	 	{
	     return sessionFactory.openSession();
	 	}

	  public boolean checkLogin(String userName,String userPassword,String usertype) 
	  	{
		  Session session = sessionFactory.openSession();
		  boolean userFound = false;
		
		  String SQL_QUERY="from Employee where email=? and password=? and category=?"; Query query =
		  session.createQuery(SQL_QUERY); 
		  query.setParameter(0,userName);
		  query.setParameter(1,userPassword);
		  query.setParameter(2,usertype);
		 
		/*
		 * String q="from Employee u where BINARY u.email = '" + userName +
		 * "' and BINARY u.password='"+
		 * userPassword+"'and BINARY category='"+usertype+"'"; Query query =
		 * session.createQuery(q);
		 */
		  
		  List list = query.list();
		
		if ((list != null) && (list.size() > 0))
		{
			userFound= true;
		}

		session.close();
		return userFound;              

	  	}

	

}
