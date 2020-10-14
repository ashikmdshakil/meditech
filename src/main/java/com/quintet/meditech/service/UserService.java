package com.quintet.meditech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quintet.meditech.model.Users;
import com.quintet.meditech.repository.UserJPARepository;

@Service
public class UserService {
	@Autowired
	private UserJPARepository userRepo;
	
	public Users getUser(String mobileNumber) {
		return userRepo.findByMobileNumber(mobileNumber);
	}
	public void updateUserLoginInfo(Users user) {
		userRepo.save(user);
	}
	public List<Users> getAllUsers(){
		return userRepo.findAll();
	}
	public void updateRole(Users user) {
		
	}
	
	
	
}
