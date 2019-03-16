package com.admin.model;

import java.util.*;

public interface AdminDAO_interface {
	
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(String adminID);
	public AdminVO findByPrimaryKey(String adminID);
	public List<AdminVO> getAll();
	
	public AdminVO login(String adminID, String password);
	public void updatePSW(AdminVO adminVO);
}
