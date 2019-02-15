package com.driver.model;
import java.sql.Date;
//com.driver.model
public class DriverVO {
	
	private String memID               ;
	private String driverID            ;
	private String plateNum            ;
	private byte[]  licence             ;  
	private byte[]  criminal            ;   
	private byte[]  trafficRecord      ;   
	private byte[]  idNum              ;   
	private byte[]  photo               ;   
	private Integer verified            ;
	private Integer banned              ;
	private Date    deadline            ;      
	private Integer onlineCar          ;
	private Integer score               ;
	private String  carType            ;       
	private Integer sharedCar          ;
	private Integer pet                 ;
	private Integer smoke               ; 
	private Integer babySeat           ;
	
	public DriverVO() {
		
	}
	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public byte[] getLicence() {
		return licence;
	}

	public void setLicence(byte[] licence) {
		this.licence = licence;
	}

	public byte[] getCriminal() {
		return criminal;
	}

	public void setCriminal(byte[] criminal) {
		this.criminal = criminal;
	}

	public byte[] getTrafficRecord() {
		return trafficRecord;
	}

	public void setTrafficRecord(byte[] trafficRecord) {
		this.trafficRecord = trafficRecord;
	}

	public byte[] getIdNum() {
		return idNum;
	}

	public void setIdNum(byte[] idNum) {
		this.idNum = idNum;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

	public Integer getBanned() {
		return banned;
	}

	public void setBanned(Integer banned) {
		this.banned = banned;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getOnlineCar() {
		return onlineCar;
	}

	public void setOnlineCar(Integer onlineCar) {
		this.onlineCar = onlineCar;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getSharedCar() {
		return sharedCar;
	}

	public void setSharedCar(Integer sharedCar) {
		this.sharedCar = sharedCar;
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

	public Integer getBabySeat() {
		return babySeat;
	}

	public void setBabySeat(Integer babySeat) {
		this.babySeat = babySeat;
	}

	

	
 

}


