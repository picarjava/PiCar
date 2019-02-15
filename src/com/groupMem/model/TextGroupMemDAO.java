package com.groupMem.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class TextGroupMemDAO {
public static void main(String[] args) {
	GroupMemJDBCDAO DAO =new GroupMemJDBCDAO();
	
	//增
//	GroupMemVO groupMemVO1 =new GroupMemVO();
//	groupMemVO1.setGroupID("G002");
//	groupMemVO1.setMemID("M002");
//	groupMemVO1.setState(0);
//	DAO.insert(groupMemVO1);
//	System.out.println(groupMemVO1);
	

	//改
//	GroupMemVO groupMemVO1 =new GroupMemVO();
//	groupMemVO1.setGroupID("G001");
//	groupMemVO1.setMemID("M002");
//	groupMemVO1.setState(0);
//	DAO.update(groupMemVO1);
//	System.out.println(groupMemVO1);
	
	
	//刪除
//	DAO.delete("G001");	
//	System.out.println("成功拉");	
	
	
	
	
	//查一
//	GroupMemVO groupMemVO = DAO.findByPrimaryKey("G001");
//	System.out.print(groupMemVO.getGroupID() + ",");
//	System.out.print(groupMemVO.getMemID() + ",");
//	System.out.print(groupMemVO.getState() + ",");
//	System.out.println("成功拉");
	
	
	
	//查全
	List<GroupMemVO> list =DAO.getAll();
	for (GroupMemVO groupMemVO : list) {
		System.out.print(groupMemVO.getGroupID() + ",");
		System.out.print(groupMemVO.getMemID() + ",");
		System.out.print(groupMemVO.getState() + ",");
		System.out.println("成功拉");
	}
	
}
}
