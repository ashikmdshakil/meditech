package com.quintet.meditech.controller;

import java.io.Console;
import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.quintet.meditech.model.*;
import com.quintet.meditech.repository.*;
import com.quintet.meditech.service.EmailService;
import com.quintet.meditech.service.UserAvatarService;
import org.apache.catalina.User;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private Token token;
	@Autowired
	private TokenJpaRepository tokenRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CategoriesJpaRepository categoriesRepo;
	@Autowired
	private Categories categories;
	@Autowired
	private ChamberJpaRepository chamberRepo;
	@Autowired
	private DoctorSlotJpaRepo doctorSlotRepo;
	@Autowired
	private AppoinmentJpaRepository appoinmentRepo;
	@Autowired
	private Prescription prescription;
	@Autowired
	private PrescriptionJpaRepo prescriptionRepo;
	@Autowired
	private PercentageJpaRepository percentageRepo;
	@Autowired
	private MedicineJpaRepository medicineRepo;
	@Autowired
	private TestJpaRepository testRepo;
	@Autowired
	private MedicineSceduleJpaRepository scheduleRepo;
	@Autowired
	private PrescriptionReport prescriptionReport;
	@Autowired
	private PrescriptionReportJpaRepository prescriptionReportRepo;
	@Autowired
	private Appoinment appoinment;
	
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

	@GetMapping("/getUserById")
	@ResponseBody
	public Users getUserById(@RequestParam("id") int id) {

		return userRepo.findById(id);
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
			System.out.println("Sign Up is successfull!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "failed";
			System.out.println("Sign Up is not successfull !");
		}
		return status;
	}
	@PostMapping(value = "registerDoctor",consumes="application/json")
	@ResponseBody
	public String reisterDoctor(@RequestBody Users user, HttpServletRequest request) {
		String status = null;
		try {
			user.setCreateDate(LocalDateTime.now());
			role.setRoleId(2);
			user.setRoles(role);
			role.getUsers().add(user);
			userRepo.save(user);
			status = "success";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "failed";

		}
		return status;
	}

	@PostMapping(value = "registerPatient",consumes="application/json")
	@ResponseBody
	public String reisterPatient(@RequestBody Users user, HttpServletRequest request) {
		String status = null;
		try {
			user.setCreateDate(LocalDateTime.now());
			role.setRoleId(1);
			user.setRoles(role);
			role.getUsers().add(user);
			userRepo.save(user);
			status = "success";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "failed";

		}
		return status;
	}

	@PostMapping(value = "registerSuperman",consumes="application/json")
	@ResponseBody
	public String reisterSuperman(@RequestBody Users user, HttpServletRequest request) {
		String status = null;
		try {
			user.setCreateDate(LocalDateTime.now());
			role.setRoleId(4);
			user.setRoles(role);
			role.getUsers().add(user);
			userRepo.save(user);
			status = "success";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "failed";

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
		System.out.println("Login is Successfull");
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
		System.out.println("Update controller works...");
		try {
			addressBook = users.getAddressBooks();
			addressBook.setUser(users);
			users.setAddressBooks(addressBook);
			userService.updateUser(users);
			for(Categories category: users.getCategories()){
				System.out.println("Category name is "+category.getName());
			}
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
			System.out.println("This is not working ....");
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
		userAvatar.setUser(user);
		System.out.println("This picture is of "+user.getName());
		user.setUserAvatar(userAvatar);
		avatarService.updateAvatar(user);
		return user;
	}
	@PostMapping(value = "mailForResetPassword")
	@ResponseBody
	public String mailForResetPassword(@RequestParam("number") String number) throws MessagingException {
		System.out.println(number);
		user = userRepo.findByMobileNumber(number);
		if(user != null){
			Token tokens = tokenRepo.findByUserUserId(user.getUserId());
			if(tokens != null){
				tokenRepo.deleteById(tokens.getId());
				System.out.println("previous token is deleted ..");
				token.setTokenString(UUID.randomUUID().toString()+LocalDateTime.now().toString());
				token.setExpiredTime(LocalDateTime.now().plusHours(24));
				token.setUser(user);
				tokenRepo.save(token);
				emailService.emailSend(token);
				System.out.println("check email ....");
			}
			else{
				System.out.println("no token is there .");
				token.setTokenString(UUID.randomUUID().toString()+LocalDateTime.now().toString());
				token.setExpiredTime(LocalDateTime.now().plusHours(24));
				token.setUser(user);
				tokenRepo.save(token);
				emailService.emailSend(token);
				System.out.println("check email ....");
			}
		}
		else{
			System.out.println("user is not valid .");
		}
		return null;
	}

	@PostMapping(value = "setPassword")
	@ResponseBody
	public String resetPassword(@RequestParam("tokenString") String tokenString, @RequestParam("password") String password){
		String message = null;
		System.out.println(tokenString);
		token = tokenRepo.findByTokenString(tokenString);
		if(token == null){
			System.out.println("no token is found ...");
		}
		else{
		System.out.println("token id is "+token.getId());
			if(token.getExpiredTime().isBefore(LocalDateTime.now())){
				System.out.println("token is expired....");
				tokenRepo.deleteById(token.getId());
			}
			else{
				message = "time is not expired";
				token.getUser().setPassword(password);
				userRepo.save(token.getUser());
				tokenRepo.deleteById(token.getId());
			}
		}
		return message;
	}
	@GetMapping("getCategories")
	@ResponseBody
	 public List<Categories> getCategories() {
		List<Categories> categories = categoriesRepo.findAll();
		return categories;
	 }
	@GetMapping("getUserCategories")
	@ResponseBody
	 public List<Categories> userCategories(@RequestParam("number") String number){
		user = userRepo.findByMobileNumber(number);
		//List<Categories> categories = userRepo.findById(user.getUserId());
		//System.out.println("Categories are "+categories.toString());
		return null;
	 }
	 @GetMapping("getDoctorList")
	 @ResponseBody
	 public List<Users> getDoctorList(@RequestParam("id") String id){
		//categories = categoriesRepo.findById(Integer.parseInt(id));
		 List<Users> usersList = userRepo.findByCategoriesId(Integer.parseInt(id));
		return usersList;
	 }
	 @PostMapping(value = "updateChamber", consumes = "application/json")
	 @ResponseBody
	 public String updateChamber(@RequestBody Chamber chamber){
		String status= null;
		 try {
			 user = userRepo.findByMobileNumber(chamber.getUser().getMobileNumber());
			 chamber.setUser(user);
			 chamberRepo.save(chamber);
			 System.out.println("Chamber updating is working .....");
			 status = "success";
		 } catch (Exception e) {
			 e.printStackTrace();
			 status = "failed";
		 }
		 return status;
	 }
	@PostMapping(value = "updateDoctorSlots", consumes = "application/json")
	@ResponseBody
	public String updateDoctorSlots(@RequestBody DoctorSlot doctorSlot){
		String status= null;
		try {
			user = userRepo.findByMobileNumber(doctorSlot.getUser().getMobileNumber());
			doctorSlot.setUser(user);
			doctorSlotRepo.save(doctorSlot);
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}
	@GetMapping("getChamber")
	@ResponseBody
	public Chamber getChamber(@RequestParam("id") int id){
		return chamberRepo.findById(id);
	}

	@GetMapping("getChamberOfDoctor")
	@ResponseBody
	public List<Chamber> getChamberOfDoctor(@RequestParam("id") int id){
		return chamberRepo.findByUserUserId(id);
	}

	@GetMapping("getChambers")
	@ResponseBody
	public List<Chamber> getChambers(){
		return chamberRepo.findAll();
	}

	@PostMapping(value = "takeAppoinment", consumes = "application/json")
	@ResponseBody
	public String takeAppoinment(@RequestBody Appoinment appoinment){
		String status= null;
		try {
			user = userRepo.findByMobileNumber(appoinment.getUser().getMobileNumber());
			//if(appoinmentRepo.existsByDoctorSlotId(appoinment.getDoctorSlot().getId())){
				//status = "taken";
			//}
			//else{
				appoinment.setUser(user);
				int presentAppoinmentNumber = appoinmentRepo.countAppoinmentsByDoctorSlotId(appoinment.getDoctorSlot().getId());
				int maxumumAppoinmentNumber = appoinment.getDoctorSlot().getMaximumNumberOfAppoinment();
				System.out.println("Present appoinment number is "+presentAppoinmentNumber);
				LocalDateTime from = appoinment.getDoctorSlot().getStartTime();
				LocalDateTime to = appoinment.getDoctorSlot().getEndTime();
				Duration duration = Duration.between(from, to);
				long durationTime= duration.toMinutes();
				long durationPerPerson = durationTime/appoinment.getDoctorSlot().getMaximumNumberOfAppoinment();
				int serialNumber = presentAppoinmentNumber + 1;
				LocalDateTime appoinmentTime = appoinment.getDoctorSlot().getStartTime().plusMinutes(durationPerPerson*serialNumber);
				appoinment.setSerialNumber(serialNumber);
				appoinment.setTime(appoinmentTime);
				if(presentAppoinmentNumber < maxumumAppoinmentNumber){
					appoinmentRepo.save(appoinment);
					status = "success";
				}
				else{
					status = "overloaded";
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}

	@GetMapping("getSlots")
	@ResponseBody
	public List<DoctorSlot> getSlots(@RequestParam("id") int id){
		return doctorSlotRepo.findByChamberId(id);
	}

	@GetMapping("getPatientList")
	@ResponseBody
	public List<Appoinment> getSlot(@RequestParam("id") int id){
		return appoinmentRepo.findByDoctorSlotId(id);
	}

	@GetMapping("myAppoinments")
	@ResponseBody
	public List<Appoinment> myAppoinments(@RequestParam("mobileNumber") String number){
		return appoinmentRepo.findByUserMobileNumber(number);
	}

	@PostMapping(value = "savePrescriptionMedicine", consumes = "application/json")
	@ResponseBody
	public String savePrescription(@RequestBody Prescription prescription){
		String status= null;
		try {
			//System.out.println("The appoinment id is "+prescription.getAppoinmentId());
			for(Medicine medicine: prescription.getMedicines()) {
				System.out.println(medicine.getId());
				System.out.println(medicine.getMedicineName());
				if (medicine.getId() == 0) {
					medicineRepo.save(medicine);
				}
			}
			prescriptionRepo.save(prescription);
			try {
				scheduleRepo.deleteAllByPrescriptionId(prescription.getId());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No prescription exists...");
			}
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}
	@GetMapping("getFullPrescription")
	@ResponseBody
	public Prescription getUserPrescription(@RequestParam("appoinmentId") int appoinmentId){
		return prescriptionRepo.findFirstByAppoinmentId(appoinmentId);
	}


	@GetMapping("getPrescriptions")
	@ResponseBody
	public List<Prescription> getPrescription(@RequestParam("id") int id){
		return prescriptionRepo.findByPatientUserId(id);
	}


	@PostMapping(value = "savePrescription", consumes = "application/json")
	@ResponseBody
	public String savePrescriptionfull(@RequestBody Prescription prescription){
		String status= null;
		try {
			for(MedicineScedule schedule: prescription.getScedules()){
				System.out.println("This medicine schedule id is "+schedule.getId());
				System.out.println(" "+schedule.getMedicine().getMedicineName()+" "+schedule.getMorning()+"+"+" "+schedule.getDay()+"+"+" "+schedule.getNight()+" "+ schedule.getDays());
				schedule.setPrescription(prescription);
				System.out.println("This medicine scedule's prescription id is "+schedule.getPrescription().getId());
				System.out.println("The mediccine id is "+schedule.getMedicine().getId());
				scheduleRepo.save(schedule);
				try {
					scheduleRepo.deleteAllNull();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for(Test test: prescription.getTests()){
				if (test.getId() == 0) {
					testRepo.save(test);
				}
			}
			prescriptionRepo.save(prescription);
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}

	@GetMapping("getAdminDoctors")
	@ResponseBody
	public List<Users> getAdminDoctors(@RequestParam("number") String number){
		return userRepo.findByAdminNumberAndRolesRoleId(number, 2);
	}
	@GetMapping("getAdminPatients")
	@ResponseBody
	public List<Users> getAdminPatients(@RequestParam("number") String number){
		return userRepo.findByAdminNumberAndRolesRoleId(number, 1);
	}
	@GetMapping("getAdminSupermen")
	@ResponseBody
	public List<Users> getAdminSupermen(@RequestParam("number") String number){
		return userRepo.findByAdminNumberAndRolesRoleId(number, 4);
	}

	@PostMapping(value = "updatePercentage", consumes = "application/json")
	@ResponseBody
	public String updatePercentage(@RequestBody Percentage percentage){
		String status= null;
		try {
			percentageRepo.save(percentage);
			status = "success";
		} catch (Exception e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}
	@GetMapping("getPercentages")
	@ResponseBody
	public List<Percentage> getPercentage(){
		return percentageRepo.findAll();
	}

	@GetMapping("medicineList")
	@ResponseBody
	public List<Medicine> getMedicineList(){
		/*for(Medicine medicine: medicineRepo.findAll()){
			System.out.println(medicine.getMedicineName());
		}*/
		return medicineRepo.findAll();
	};
	@GetMapping("getAppDoctors")
	@ResponseBody
	public List<Users> getAppDoctors(){
		return userRepo.findByRolesRoleId(2);
	};

	@GetMapping("getTestNames")
	@ResponseBody
	public List<Test> getTests(){
		return testRepo.findAll();
	};

	@PostMapping(value = "uploadReports")
	@ResponseBody
	public String previousReport(@RequestParam("reports") MultipartFile files[], @RequestParam("appoinmentId") String appoinmentId) throws IOException {
		String status = null;
		try {
			appoinment.setId(Integer.parseInt(appoinmentId));
			for(MultipartFile file : files){
				prescriptionReport.setImage(file.getBytes());
				prescriptionReport.setAppoinment(appoinment);
				appoinment.getReports().add(prescriptionReport);
				prescriptionReportRepo.save(prescriptionReport);
			}
			//appoinmentRepo.save(appoinment);
			status = "success";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}
	@GetMapping("getPreviousReports")
	@ResponseBody
	public List<PrescriptionReport> getPreviousReports(@RequestParam("appoinmentId") int id){
		return prescriptionReportRepo.findByAppoinmentId(id);
	}

	@GetMapping("searchDoctors")
	@ResponseBody
	public List<Users> searchDoctors(@RequestParam("keyWords") String keyWords) {
		return userRepo.findByNameIsContaining(keyWords);
	}
	}


