package model;

public class GroupReportVO {
	private String greportId;
	private String memId;	
	private String groupId;	
	private String adminId;
	private Integer state;	

	public GroupReportVO() {}

	public String getGreportId() {
		return greportId;
	}

	public void setGreportId(String greportId) {
		this.greportId = greportId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
