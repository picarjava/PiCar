package com.admin.model;

import java.util.*;
import java.sql.*;
import com.admin.model.AdminVO;

public class AdminService {
	
	private AdminDAO_interface dao;
	public AdminService() {
		dao = new AdminDAO();
	}

	public AdminVO addAdmin(String adminName, String password, Integer isEmp) {
		
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminName(adminName);
		adminVO.setPassword(password);
		adminVO.setIsEmp(isEmp); 
		dao.insert(adminVO);
		return adminVO;
	}
	
	public AdminVO updateAdmin(String adminID, String adminName, String password, Integer isEmp) {
		AdminVO adminVO = new AdminVO();
		
		adminVO.setAdminID(adminID);
		adminVO.setAdminName(adminName);
		adminVO.setPassword(password);
		adminVO.setIsEmp(isEmp);
		dao.update(adminVO);
		
		return adminVO;
		
	}
	
	public void deleteAdmin(String adminID) {
		dao.delete(adminID);
	}
	
	public AdminVO getOneAdmin(String adminID) {
		return dao.findByPrimaryKey(adminID);
	}
	
	public List<AdminVO> getAll() {
		return dao.getAll();
	}
	
	
}
