package com.groupReport.model;

import java.sql.Date;

public class GroupReportVO implements java.io.Serializable{
	
	private String greportID;
	private String memID;
	private String groupID;
	private String adminID;
	private String content;
	private Date time;
	private Integer state;
	
	public GroupReportVO() {}

	public String getGreportID() {
		return greportID;
	}

	public void setGreportID(String greportID) {
		this.greportID = greportID;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
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
