package com.bidmore.model;

public class RoleModel {
	private int roleId;
	private String role; // "user" , "admin"
	
	public RoleModel() {
		
	}
	
	public RoleModel(int roleId, String role) {
		this.roleId=roleId;
		this.role=role;
		
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleID) {
		this.roleId = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
	

}
