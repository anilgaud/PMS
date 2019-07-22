package com.kgate.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.dao.LoginDao2;

@Service
public class LoginService2Impl  implements LoginService2{
	
	@Autowired
	private LoginDao2 ld;
			
			 public void setLoginDAO(LoginDao2  ld) 
			 {
				this.ld=ld;
			 }

			@Override
			@Transactional
			public boolean checkLogin(String username, String password,String usertype) {
			
				return ld.checkLogin(username, password, usertype);
			}

		
			 
			
	}

			


