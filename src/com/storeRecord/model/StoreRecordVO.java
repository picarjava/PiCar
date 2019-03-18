package com.storeRecord.model;

import java.sql.Timestamp;

public class StoreRecordVO implements java.io.Serializable{
	
	private String storeID;
	private String memID;
	private Timestamp saveDate;
	private Integer amount;
	private String orderID;
	
	public String getStoreID() {
		return storeID;
	}
	
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	
	public String getMemID() {
		return memID;
	}
	
	public void setMemID(String memID) {
		this.memID = memID;
	}
	
	public Timestamp getSaveDate() {
		return saveDate;
	}
	
	public void setSaveDate(Timestamp saveDate) {
		this.saveDate = saveDate;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
}
