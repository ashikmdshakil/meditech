package com.quintet.meditech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.quintet.meditech.model.Users;
import com.quintet.meditech.repository.UserJPARepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	@Autowired
	private UserJPARepository userRepo;
	@Autowired
	private Users user;
	@Override
	public UserDetails loadUserByUsername(String number){
		// TODO Auto-generated method stub
		user = userRepo.findByMobileNumber(number);
		return new ApplicationUserDetails(user);
	}
	

}
