package com.driverReport.model;

import java.sql.Date;
import java.util.List;

public class DriverReportService {
	
	private DriverReportDAO_interface dao;
	
	public DriverReportService() {
		dao = new DriverReportDAO();
	}
	
	//小編新增getOneByOrderID方法
	public DriverReportVO getOneByOrderID(String orderID) {
		return dao.findByOrderID(orderID);
	}
	
	public DriverReportVO addDriverReport(String memID, String adminID, String orderID,String content, Date time,Integer state) {
		
		DriverReportVO driverReportVO = new DriverReportVO();
		
		driverReportVO.setMemID(memID);
		driverReportVO.setAdminID(adminID);
		driverReportVO.setOrderID(orderID);
		driverReportVO.setContent(content);
		driverReportVO.setTime(time);
		driverReportVO.setState(state);
		dao.insert(driverReportVO);
		return driverReportVO;
		
	} 
	
	public DriverReportVO updateDriverReport(String dreportID,String memID, String adminID, String orderID,String content, Date time,Integer state) {
		
		DriverReportVO driverReportVO = new DriverReportVO();
		
		driverReportVO.setDreportID(dreportID);
		driverReportVO.setMemID(memID);
		driverReportVO.setAdminID(adminID);
		driverReportVO.setOrderID(orderID);
		driverReportVO.setContent(content);
		driverReportVO.setTime(time);
		driverReportVO.setState(state);
		dao.update(driverReportVO);
		
		return driverReportVO;
		
	}
	
	public void deleteDriverReport(String dreportID) {
		dao.delete(dreportID);
	}
	
	public DriverReportVO getOneDriverReport(String dreportID) {
		return dao.findByPrimaryKey(dreportID);
	}
	
	public List<DriverReportVO> getAll() {
		return dao.getAll();
	}
	

}
