package com.quintet.meditech.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String mobileNumber;
	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;
	private String reminderQueryQuestion;
	private String facebookId;
	private String gmailId;
	private String twitterId;
	private LocalDateTime loginDate;
	private String loginIp;
	private LocalDateTime lastLoginDate;
	private String lastLoginIp;
	private LocalDateTime lastFailedLoginDate;
	@Column(columnDefinition = "integer default 0")
	private int failedLoginAttempts;
	@Column(columnDefinition = "boolean default false")
	private boolean aggreedToTermOfUse;
	@Column(columnDefinition = "boolean default false")
	private boolean emailVerified ;
	@ManyToMany(mappedBy = "users")
	private List<Account> account;
	@ManyToOne(fetch =FetchType.EAGER)
	@JsonIgnoreProperties("users")
	private Roles roles;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private UserAvatar userAvatar;
	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("user")
	private AddressBook addressBooks;
	@OneToOne
	@JsonIgnoreProperties("user")
	private Speciality speciality;
	@OneToOne
	@JsonIgnoreProperties("user")
	private Degree degree;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Appoinment> appoinments;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<DoctorSlot> doctorSlots;
	@ManyToMany
	private List<Categories> categories;
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties("user")
	private List<Chamber> chambers;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Prescription> prescriptions;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getReminderQueryQuestion() {
		return reminderQueryQuestion;
	}

	public void setReminderQueryQuestion(String reminderQueryQuestion) {
		this.reminderQueryQuestion = reminderQueryQuestion;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGmailId() {
		return gmailId;
	}

	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public LocalDateTime getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDateTime loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public LocalDateTime getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(LocalDateTime lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public boolean isAggreedToTermOfUse() {
		return aggreedToTermOfUse;
	}

	public void setAggreedToTermOfUse(boolean aggreedToTermOfUse) {
		this.aggreedToTermOfUse = aggreedToTermOfUse;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public UserAvatar getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(UserAvatar userAvatar) {
		this.userAvatar = userAvatar;
	}

	public AddressBook getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(AddressBook addressBooks) {
		this.addressBooks = addressBooks;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public List<Appoinment> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(List<Appoinment> appoinments) {
		this.appoinments = appoinments;
	}

	public List<DoctorSlot> getDoctorSlots() {
		return doctorSlots;
	}

	public void setDoctorSlots(List<DoctorSlot> doctorSlots) {
		this.doctorSlots = doctorSlots;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Chamber> getChambers() {
		return chambers;
	}

	public void setChambers(List<Chamber> chambers) {
		this.chambers = chambers;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
}
