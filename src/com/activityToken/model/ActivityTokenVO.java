package com.activityToken.model;

import java.io.Serializable;

public class ActivityTokenVO implements Serializable{
	
	private String mem_id;
	private String activity_id;
	private Integer token_amount;
	private java.sql.Date deadline;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}
	public Integer getToken_amount() {
		return token_amount;
	}
	public void setToken_amount(Integer token_amount) {
		this.token_amount = token_amount;
	}
	public java.sql.Date getDeadline() {
		return deadline;
	}
	public void setDeadline(java.sql.Date deadline) {
		this.deadline = deadline;
	}	

}
