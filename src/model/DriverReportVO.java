package model;

import java.sql.Date;

public class DriverReportVO {
	private String dreportId;	
	private String memId;	
	private String adminId;	
	private String orderId;
	private String content;
	private Date time;
	private Integer state;
	
	public DriverReportVO() {}

	public String getDreportId() {
		return dreportId;
	}

	public void setDreportId(String dreportId) {
		this.dreportId = dreportId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
