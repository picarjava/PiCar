package com.driver.model;
import java.sql.Date;
//com.driver.model
public class DriverVO {
	
	private String mem_Id               ;
	private String driver_id            ;
	private String plate_Num            ;
	private byte[]  licence             ;  
	private byte[]  criminal            ;   
	private byte[]  traffic_record      ;   
	private byte[]  id_Num              ;   
	private byte[]  photo               ;   
	private Integer verified            ;
	private Integer banned              ;
	private Date    deadline            ;      
	private Integer online_car          ;
	private Integer score               ;
	private String  car_type            ;       
	private Integer shared_Car          ;
	private Integer pet                 ;
	private Integer smoke               ; 
	private Integer baby_Seat           ;
	
	public DriverVO() {
		
	}

	public String getMem_Id() {
		return mem_Id;
	}
	public void setMem_Id(String mem_Id) {
		this.mem_Id = mem_Id;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getPlate_Num() {
		return plate_Num;
	}
	public void setPlate_Num(String plate_Num) {
		this.plate_Num = plate_Num;
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
	public byte[] getTraffic_record() {
		return traffic_record;
	}
	public void setTraffic_record(byte[] traffic_record) {
		this.traffic_record = traffic_record;
	}
	public byte[] getId_Num() {
		return id_Num;
	}
	public void setId_Num(byte[] id_Num) {
		this.id_Num = id_Num;
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
	public Integer getOnline_car() {
		return online_car;
	}
	public void setOnline_car(Integer online_car) {
		this.online_car = online_car;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public Integer getShared_Car() {
		return shared_Car;
	}
	public void setShared_Car(Integer shared_Car) {
		this.shared_Car = shared_Car;
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
	public Integer getBaby_Seat() {
		return baby_Seat;
	}
	public void setBaby_Seat(Integer baby_Seat) {
		this.baby_Seat = baby_Seat;
	}
 

}


