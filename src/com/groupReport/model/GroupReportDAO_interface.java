package com.groupReport.model;

import java.util.*;

public interface GroupReportDAO_interface {
	
	public void insert(GroupReportVO groupReportVO);
	public void update(GroupReportVO groupReportVO);
	public void delete(String greportID); //greport_id為PK
	public GroupReportVO findByPrimaryKey(String greportID);
	public List<GroupReportVO> getAll();
}
