package com.quintet.meditech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.quintet.meditech.model.Users;

@Service
public class ApplicationUserDetailsService implements UserDetailsService{
	@Autowired
	private Users user;
	//@Autowired
	//private jpaRepository studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String phoneNumber) {
		// TODO Auto-generated method stub
		//student = studentRepo.findByMail(mail);
		return new ApplicationUserDetails(user);
	}

}
