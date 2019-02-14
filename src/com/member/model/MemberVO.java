package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	
	private String memid;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String creditcard;
	private Integer pet;
	private Integer smoke;
	private Integer gender;
	private Integer token; 
	private Integer activitytoken;
	private Date birthday;
	private Integer verified;
	private Integer babysite;
	
	
	public String getMemid() {
		return memid;
	}
	
	public void setMemid(String memid) {
		this.memid = memid;
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
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCreditcard() {
		return creditcard;
	}
	
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	
	public Integer getPet() {
		return pet;
	}
	
	public void setPet(Integer pet) {
		this.pet = pet;
	}
	public Integer getSmoke() {
		return smoke;
	}
	public void setSmoke(Integer smoke) {
		this.smoke = smoke;
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Integer getToken() {
		return token;
	}
	public void setToken(Integer token) {
		this.token = token;
	}
	
	public Integer getActivitytoken() {
		return activitytoken;
	}
	
	public void setActivitytoken(Integer activitytoken) {
		this.activitytoken = activitytoken;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	public Integer getVerified() {
		return verified;
	}
	
	public void setVerified(Integer verified) {
		this.verified = verified;
	}
	
	public Integer getBabysite() {
		return babysite;
	}
	
	public void setBabysite(Integer babysite) {
		this.babysite = babysite;
	}
	
	
	
	
	
}
