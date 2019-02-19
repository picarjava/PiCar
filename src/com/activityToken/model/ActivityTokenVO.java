package com.activityToken.model;

import java.io.Serializable;

public class ActivityTokenVO implements Serializable{
	
	private String memID;
	private String activityID;
	private Integer tokenAmount;
	private java.sql.Date deadline;
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getActivityID() {
		return activityID;
	}
	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	public Integer getTokenAmount() {
		return tokenAmount;
	}
	public void setTokenAmount(Integer tokenAmount) {
		this.tokenAmount = tokenAmount;
	}
	public java.sql.Date getDeadline() {
		return deadline;
	}
	public void setDeadline(java.sql.Date deadline) {
		this.deadline = deadline;
	}	

}
