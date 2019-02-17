package com.paymentRecord.model;

import java.sql.Date;

public class PaymentRecordVO implements java.io.Serializable{
	
	private String paymentID;
	private String driverID;
	private Integer payAnount;
	private Date payTime;
	
	public String getPaymentID() {
		return paymentID;
	}
	
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	
	public String getDriverID() {
		return driverID;
	}
	
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	
	public Integer getPayAnount() {
		return payAnount;
	}
	
	public void setPayAnount(Integer payAnount) {
		this.payAnount = payAnount;
	}
	
	public Date getPayTime() {
		return payTime;
	}
	
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
}
