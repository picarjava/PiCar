package com.groupBand.model;

import java.sql.*;

public class GroupBandVO implements java.io.Serializable {

	private String groupID;
	private String content;
	private Timestamp launchTime;
	private String introduction;
	private Integer groupStatus;
	private Integer currenTnum;
	private Integer upperLimit;
	private Integer lowerLimit;
	private String groupName;
	private Integer groupLeader;
	private String startLoc;
	private String endLoc;
	private Integer privates;
	private byte[] photo;
	private String groupType;
	private Integer totalAmout;
	private Date startTime;
	private Integer rate;
	private String note;
	
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupId) {
		this.groupID = groupId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getLaunchTime() {
		return launchTime;
	}
	public void setLaunchTime(Timestamp launchTime) {
		this.launchTime = launchTime;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(Integer groupStatus) {
		this.groupStatus = groupStatus;
	}
	public Integer getCurrenTnum() {
		return currenTnum;
	}
	public void setCurrenTnum(Integer currenTnum) {
		this.currenTnum = currenTnum;
	}
	public Integer getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(Integer groupLeader) {
		this.groupLeader = groupLeader;
	}
	public String getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}
	public String getEndLoc() {
		return endLoc;
	}
	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}
	public Integer getPrivates() {
		return privates;
	}
	public void setPrivates(Integer privates) {
		this.privates = privates;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Integer getTotalAmout() {
		return totalAmout;
	}
	public void setTotalAmout(Integer totalAmout) {
		this.totalAmout = totalAmout;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}
