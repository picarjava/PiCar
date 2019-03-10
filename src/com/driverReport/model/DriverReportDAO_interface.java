package com.driverReport.model;

import java.util.*;

public interface DriverReportDAO_interface {
	public void insert(DriverReportVO driverReportVO);
	public void update(DriverReportVO driverReportVO);
	public void delete(String dreportID); //dreport_id為PK
	public DriverReportVO findByPrimaryKey(String dreportID);
	public List<DriverReportVO> getAll();
	//小編新增findByOrderID方法
		public DriverReportVO findByOrderID(String orderID);
}
