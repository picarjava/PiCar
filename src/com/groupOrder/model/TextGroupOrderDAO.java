package com.groupOrder.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TextGroupOrderDAO {
	public static void main(String[] args) throws ParseException {
	GruopOrderJDBCDAO DAO =new GruopOrderJDBCDAO();
	GroupOrderVO groupOrderVO =new GroupOrderVO();
	groupOrderVO.setDriverID("DR001");
	groupOrderVO.setMemID("M001");
	groupOrderVO.setState(0);
	groupOrderVO.setTotalAmout(1500);
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	groupOrderVO.setStartTime(new Date(simpleDateFormat.parse("2019-02-14").getTime()));
//	groupOrderVO.s
//	groupOrderVO.s
//	groupOrderVO.s
//	groupOrderVO.s
//	groupOrderVO.s
	
//	p.setString(1, groupOrderVO.g);
//	p.setString(2, groupOrderVO.g());
//	p.setInt(3,groupOrderVO.g);
//	p.setInt(4,groupOrderVO.g);
//	p.setTimestamp(5,groupOrderVO.g);
//	p.setDate(6,groupOrderVO.g);
//	p.setDate(7,groupOrderVO.getEndTime());
//	p.setDouble(8,groupOrderVO.getStartLng());
//	p.setDouble(9,groupOrderVO.getStartLat());
//	p.setDouble(10,groupOrderVO.getEndLng());
//	p.setDouble(11,groupOrderVO.getEndLat());
//	p.setInt(12,groupOrderVO.getOrderType());
//	p.setInt(13,groupOrderVO.getRate());
//	p.setString(14,groupOrderVO.getNote());
//	
	}
}
