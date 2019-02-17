package com.storeRecord.model;

import java.sql.Date;

public class StoreRecordVO implements java.io.Serializable{
	
	private String recordID;
	private String memID;
	private Date saveDate;
	private Integer amount;
	
	public String getRecordID() {
		return recordID;
	}
	
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	
	public String getMemID() {
		return memID;
	}
	
	public void setMemID(String memID) {
		this.memID = memID;
	}
	
	public Date getSaveDate() {
		return saveDate;
	}
	
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
