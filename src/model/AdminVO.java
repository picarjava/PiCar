package model;

/**
 * @author Jim
 *
 */
public class AdminVO {
	private String adminId;
	private String adminName;	
	private String password;
	private	Integer isEmp;
	
	public AdminVO() {}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsEmp() {
		return isEmp;
	}

	public void setIsEmp(Integer isEmp) {
		this.isEmp = isEmp;
	}
	
	
}
