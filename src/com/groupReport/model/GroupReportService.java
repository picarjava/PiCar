package com.groupReport.model;

import java.sql.Date;
import java.util.*;

public class GroupReportService {
	
	private GroupReportDAO_interface dao;
	public GroupReportService() {
		dao = new GroupReportDAO();
	}
	
	public GroupReportVO addGroupReport(String memID, String groupID, String adminID, String content, Date time, Integer state) {
		
		GroupReportVO groupReportVO = new GroupReportVO();
		
		groupReportVO.setMemID(memID);
		groupReportVO.setGroupID(groupID);
		groupReportVO.setAdminID(adminID);
		groupReportVO.setContent(content);
		groupReportVO.setTime(time);
		groupReportVO.setState(state);
		dao.insert(groupReportVO);
		return groupReportVO;
		
	} 
	
	public GroupReportVO updateGroupReport(String greportID, String memID, String groupID, String adminID, String content, Date time , Integer state) {
		
		GroupReportVO groupReportVO = new GroupReportVO();
		
		groupReportVO.setGreportID(greportID);
		groupReportVO.setMemID(memID);
		groupReportVO.setGroupID(groupID);
		groupReportVO.setAdminID(adminID);
		groupReportVO.setContent(content);
		groupReportVO.setTime(time);
		groupReportVO.setState(state);
		dao.update(groupReportVO);
		
		return groupReportVO;
		
	}
	
	public void deleteGroupReport(String greportID) {
		dao.delete(greportID);
	}
	
	public GroupReportVO getOneGroupReport(String greportID) {
		return dao.findByPrimaryKey(greportID);
	}
	
	public List<GroupReportVO> getAll() {
		return dao.getAll();
	}
	public void insertGroupStmt(String memID,String groupID,String content) {
		dao.insertGroupStmt(memID, groupID, content);
	}

}
