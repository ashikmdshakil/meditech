package com.quintet.meditech.controller;

import java.io.Console;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.quintet.meditech.model.AddressBook;
import com.quintet.meditech.model.UserAvatar;
import com.quintet.meditech.repository.UserAvatarJpaRepository;
import com.quintet.meditech.service.UserAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	private AddressBook addressBook;
	@Autowired
	private UserAvatar userAvatar;
	@Autowired
	private UserAvatarService avatarService;
	@Autowired
	private UserAvatarJpaRepository avatarRepo;
	
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

	@PostMapping(value = "removeUser", consumes = "application/json")
	@ResponseBody
	public Users deleteUser(@RequestBody Users users){
		System.out.println(users.getEmail());
		userService.deleteUser(users);
		return users;
	}
	@PostMapping(value = "updateUser", consumes = "application/json")
	@ResponseBody
	public String updateUser(@RequestBody Users users){
		String status = null;
		try {
			//addressBook = users.getAddressBooks();
			userService.updateUser(users);
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}
	@PostMapping(value = "updateAvatar")
	@ResponseBody
	public Users updateAvatar(@RequestParam("profileImage") MultipartFile file, @RequestParam("userId") String userId) throws IOException {
		user = userService.findUser(Integer.parseInt(userId));
		System.out.println(file.getContentType());
		//String base64 = Base64.getEncoder().encodeToString(file.getBytes());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		userAvatar.setImage(file.getBytes());
		user.setUserAvatar(userAvatar);
		avatarService.updateAvatar(user);
		return user;
	}
	//@GetMapping(value = "getImage")
	//@ResponseBody
	//public HttpEntity<byte[]> getImage(){
		//byte[] image = avatarRepo.findByAvatarId(2).getImage();
		//HttpHeaders header = new HttpHeaders();
		//header.setContentType(MediaType.IMAGE_JPEG);
		//header.setContentLength(image.length);
		//System.out.println(image);
		//return new HttpEntity<byte[]>(image, header);
	//}

	
}

