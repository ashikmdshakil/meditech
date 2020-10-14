package com.quintet.meditech.controller;

import java.io.Console;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quintet.meditech.model.Roles;
import com.quintet.meditech.model.Users;
import com.quintet.meditech.repository.UserJPARepository;
import com.quintet.meditech.service.RoleService;
import com.quintet.meditech.service.UserService;

@Controller
public class ApplicationController {
	@Autowired
	private UserJPARepository userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private Users user;
	@Autowired
	private Roles role;
	
	@GetMapping("/")
	public String homePage() {
		return "index.html";
	}
	
	@GetMapping("/getUsers")
	@ResponseBody
	public List<Users> getUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("/getUser")
	@ResponseBody
	public Users getUser(@RequestParam("number") String mobileNumber) {
		return userService.getUser(mobileNumber);
	}
	
	@PostMapping(value = "signup",consumes="application/json")
	@ResponseBody
	public String signupUser(@RequestBody Users user, HttpServletRequest request) {
		String status = null; 
		try {
			user.setCreateDate(LocalDateTime.now());
			role.setRoleId(1);
			user.setRoles(role);
			role.getUsers().add(user);
			userRepo.save(user);
			status = "success";
			System.out.println("Operation is successfull !");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "failed";
			System.out.println("Operation is not successfull !");
		}
		return status;
	}
	
	@GetMapping(value = "login")
	@ResponseBody
	public Principal login(Principal userPrincipal, @RequestParam("number") String number, HttpServletRequest request ) {
		user = userService.getUser(number);
		user.setLastLoginDate(user.getLoginDate());
		user.setLoginDate(LocalDateTime.now());
		user.setLastLoginIp(user.getLoginIp());
		user.setLoginIp(request.getRemoteAddr());
		userService.updateUserLoginInfo(user);
		System.out.println("server isrunning ......");
		return userPrincipal;
	}
	@PostMapping("logoutUser")
	@ResponseBody
	public Principal logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
 		SecurityContextHolder.clearContext();
		return request.getUserPrincipal();
	}
	
	@PostMapping(value = "assignRole", consumes = "application/json")
	@ResponseBody
	public Users assignRole(@RequestBody Users users) {
		
		userRepo.updateUserRole(users.getRoles().getRoleId(), users.getMobileNumber());
		return users;
	}
	 
	
	@GetMapping("systemRoles")
	@ResponseBody
	public List<Roles> getSystemRoles(){
		return roleService.getSystemRoles();
	}
}

