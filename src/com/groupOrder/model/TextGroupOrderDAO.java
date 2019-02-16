package com.groupOrder.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TextGroupOrderDAO {
	public static void main(String[] args) throws ParseException {
	GroupOrderJDBCDAO DAO =new GroupOrderJDBCDAO();
	
//	//增
//	GroupOrderVO groupOrderVO =new GroupOrderVO();
//	
//	groupOrderVO.setDriverID("DR001");
//	groupOrderVO.setMemID("M001");
//	groupOrderVO.setState(0);
//	groupOrderVO.setTotalAmout(1500);
//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	groupOrderVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//	groupOrderVO.setEndTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//	groupOrderVO.setStartLng(24.555);
//	groupOrderVO.setStartLat(24.555);
//	groupOrderVO.setEndLng(24.555);
//	groupOrderVO.setEndLat(24.555);	
//	groupOrderVO.setOrderType(0);
//	groupOrderVO.setRate(0);
//	groupOrderVO.setNote("無");
//	
//	DAO.insert(groupOrderVO);
//	System.out.println("成功拉");

	
	
//改
//	GroupOrderVO groupOrderVO =new GroupOrderVO();
//	
//	groupOrderVO.setDriverID("DR002");
//	groupOrderVO.setMemID("M001");
//	groupOrderVO.setState(0);
//	groupOrderVO.setTotalAmout(1500);
//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	groupOrderVO.setStartTime(new Date(simpleDateFormat.parse("2018-02-14").getTime()));
//	groupOrderVO.setEndTime(new Date(simpleDateFormat.parse("2018-02-14").getTime()));
//	groupOrderVO.setStartLng(24.555);
//	groupOrderVO.setStartLat(24.555);
//	groupOrderVO.setEndLng(24.555);
//	groupOrderVO.setEndLat(24.555);	
//	groupOrderVO.setOrderType(0);
//	groupOrderVO.setRate(0);
//	groupOrderVO.setNote("無");
//	groupOrderVO.setGorderID("GODR001");
//	System.out.println("成功拉");	
//	DAO.update(groupOrderVO);
//	System.out.println("成功拉");	
	

//刪除
//	DAO.delete("GODR002");	
//	System.out.println("成功拉");	
	
//查一
//	GroupOrderVO groupOrderVO = DAO.findByPrimaryKey("GODR002");
//	System.out.print(groupOrderVO.getGorderID() + ",");
//	System.out.print(groupOrderVO.getDriverID() + ",");
//	System.out.print(groupOrderVO.getMemID() + ",");
//	System.out.print(groupOrderVO.getState() + ",");
//	System.out.print(groupOrderVO.getTotalAmout() + ",");
//	System.out.print(groupOrderVO.getLaunchTime() + ",");
//	System.out.print(groupOrderVO.getStartTime() + ",");
//	System.out.print(groupOrderVO.getEndTime() + ",");
//	System.out.print(groupOrderVO.getStartLng() + ",");
//	System.out.print(groupOrderVO.getStartLat() + ",");
//	System.out.print(groupOrderVO.getEndLng() + ",");
//	System.out.print(groupOrderVO.getEndLat() + ",");
//	System.out.print(groupOrderVO.getOrderType() + ",");
//	System.out.print(groupOrderVO.getRate() + ",");
//	System.out.print(groupOrderVO.getNote() + ",");
//	
//	System.out.println("成功拉");
	
	//查多
	List<GroupOrderVO> list =DAO.getAll();
	
	for (GroupOrderVO groupOrderVO : list) {
	System.out.print(groupOrderVO.getGorderID() + ",");
	System.out.print(groupOrderVO.getDriverID() + ",");
	System.out.print(groupOrderVO.getMemID() + ",");
	System.out.print(groupOrderVO.getState() + ",");
	System.out.print(groupOrderVO.getTotalAmout() + ",");
	System.out.print(groupOrderVO.getLaunchTime() + ",");
	System.out.print(groupOrderVO.getStartTime() + ",");
	System.out.print(groupOrderVO.getEndTime() + ",");
	System.out.print(groupOrderVO.getStartLng() + ",");
	System.out.print(groupOrderVO.getStartLat() + ",");
	System.out.print(groupOrderVO.getEndLng() + ",");
	System.out.print(groupOrderVO.getEndLat() + ",");
	System.out.print(groupOrderVO.getOrderType() + ",");
	System.out.print(groupOrderVO.getRate() + ",");
	System.out.print(groupOrderVO.getNote() + ",");
	
	System.out.println("成功拉");	
	}
	}
}
