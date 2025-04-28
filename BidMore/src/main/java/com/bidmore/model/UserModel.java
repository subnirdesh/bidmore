package com.bidmore.model;

import java.security.Timestamp;
import java.time.LocalDate;

public class UserModel {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private LocalDate birthDate;
	private String firstName;
	private String lastName;
	private RoleModel role;
	private Timestamp registrationDate;
	private String status; // "active" , "inactive", "deleted", "locked"
	private String imageUrl;

	// Default Constructor
	public UserModel() {

	}

	// Parameterized Constructor with RoleModel Object
	public UserModel(int userId, String userName, String password, String email, String phone, LocalDate birthdate,
			String firstName, String lastName, RoleModel role, Timestamp registrationDate, String status) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthdate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.registrationDate = registrationDate;
		this.status = status;
	}

	// Parameterized Constructor with roleID
	public UserModel(int userId, String userName, String password, String email, String phone, LocalDate birthdate,
			String firstName, String lastName, int roleId, Timestamp registrationDate, String status) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthdate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = new RoleModel(roleId, null); // Create Role with just the ID
		this.registrationDate = registrationDate;
		this.status = status;
	}

	public UserModel(String userName, String password, String email, String phone, LocalDate birthdate, String firstName,
			String lastName,String imageUrl) {
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthdate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl=imageUrl;
	}
	
	public UserModel(String userName,String password) {
		this.userName=userName;
		this.password=password;
				
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthdate) {
		this.birthDate = birthdate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleModel getRole() {
		return role;
	}

	public int getRoleId() {
		return role != null ? this.role.getRoleId() : 0;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public void setRoleId(int roleId) {
		if (this.role == null) {
			this.role = new RoleModel(roleId, null);
		} else {
			this.role.setRoleId(roleId);
		}
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		imageUrl = imageUrl;
	}

}
