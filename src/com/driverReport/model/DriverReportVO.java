package com.driverReport.model;

import java.sql.Date;

public class DriverReportVO implements java.io.Serializable{
	private String dreportID;	
	private String memID;	
	private String adminID;	
	private String orderID;
	private String content;
	private Date time;
	private Integer state;
	
	public DriverReportVO() {}

	public String getDreportID() {
		return dreportID;
	}

	public void setDreportID(String dreportID) {
		this.dreportID = dreportID;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	

}
