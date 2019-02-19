package com.activity.model;

import java.io.Reader;
import java.io.Serializable;
import java.sql.Date;

public class ActivityVO implements Serializable {
	/*SQL型別轉換參考JDBC P29*/
	private String activityID;
	private String activityName;
	private String activityInfo;
	private Date activityStart;
	private Date activityEnd;
	private String activityCode;
	private Integer tokenAmount;
	/*將圖片設為byte[]型別，以利於在DAO中使用getBytes()、setBytes()對VO進行操作*/
	private byte[] activityPost;
	
	/*由eclisps自動產生*/
	public String getActivityID() {
		return activityID;
	}
	public void setActivityID(String activity_Id) {
		this.activityID = activity_Id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activity_Name) {
		this.activityName = activity_Name;
	}
	public String getActivityInfo() {
		return activityInfo;
	}
	public void setActivityInfo(String activity_Info) {
		this.activityInfo = activity_Info;
	}
	public Date getActivityStart() {
		return activityStart;
	}
	public void setActivityStart(Date activity_Start) {
		this.activityStart = activity_Start;
	}
	public Date getActivityEnd() {
		return activityEnd;
	}
	public void setActivityEnd(Date activity_End) {
		this.activityEnd = activity_End;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activity_Code) {
		this.activityCode = activity_Code;
	}
	public Integer getTokenAmount() {
		return tokenAmount;
	}
	public void setTokenAmount(Integer token_Amount) {
		this.tokenAmount = token_Amount;
	}
	public byte[] getActivityPost() {
		return activityPost;
	}
	public void setActivityPost(byte[] activity_Post) {
		this.activityPost = activity_Post;
	}

}
