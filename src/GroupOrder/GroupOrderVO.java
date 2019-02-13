package GroupOrder;

import java.sql.Date;
import java.sql.Timestamp;

public class GroupOrderVO {
	private String gorderId;
	private String driverId;
	private String memId;
	private Integer state;
	private Integer totalAmout;
	private Timestamp launchTime;
	private Date startTime;
	private Date endTime;
	private Double startLng;
	private Double startLat; 
	private Double endLng;
	private Double endLat;
	private Integer orderType;
	private Integer rate;
	private String note;
	public String getGorderId() {
		return gorderId;
	}
	public void setGorderId(String gorderId) {
		this.gorderId = gorderId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getTotalAmout() {
		return totalAmout;
	}
	public void setTotalAmout(Integer totalAmout) {
		this.totalAmout = totalAmout;
	}
	public Timestamp getLaunchTime() {
		return launchTime;
	}
	public void setLaunchTime(Timestamp launchTime) {
		this.launchTime = launchTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getStartLng() {
		return startLng;
	}
	public void setStartLng(Double startLng) {
		this.startLng = startLng;
	}
	public Double getStartLat() {
		return startLat;
	}
	public void setStartLat(Double startLat) {
		this.startLat = startLat;
	}
	public Double getEndLng() {
		return endLng;
	}
	public void setEndLng(Double endLng) {
		this.endLng = endLng;
	}
	public Double getEndLat() {
		return endLat;
	}
	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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
