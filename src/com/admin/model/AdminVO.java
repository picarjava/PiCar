package com.admin.model;

public class AdminVO implements java.io.Serializable{
	
	private String adminID;
	private String adminName;
	private String email;
	private String password;
	private Integer isEmp;
	
	public AdminVO() {}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
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

	public Integer getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(Integer isEmp) {
		this.isEmp = isEmp;
	}
	
	
}
