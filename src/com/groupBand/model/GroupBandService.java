package com.groupBand.model;

//import java.io.IOException;
//import java.sql.Date;
import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class GroupBandService {

	private GroupBandDAO_interface dao;

	public GroupBandService() {
		dao = new GroupBandDAO();
	}

	public GroupBandVO addGroupBand(String content, String introduction, Integer groupStatus, Integer currenTnum,
			Integer upperLimit, Integer lowerLimit, String groupName, String groupLeader, String startLoc,
			String endLoc, Integer privates, byte[] photo, String groupType, Integer totalAmout, Timestamp startTime,
			Integer rate, String note,Integer groupKind) {

		GroupBandVO groupBandVO = new GroupBandVO();

		groupBandVO.setContent(content);
		groupBandVO.setIntroduction(introduction);
		groupBandVO.setGroupStatus(groupStatus);
		groupBandVO.setCurrenTnum(currenTnum);
		groupBandVO.setUpperLimit(upperLimit);
		groupBandVO.setLowerLimit(lowerLimit);
		groupBandVO.setGroupName(groupName);
		groupBandVO.setGroupLeader(groupLeader);
		groupBandVO.setStartLoc(startLoc);
		groupBandVO.setEndLoc(endLoc);
		groupBandVO.setPrivates(privates);
		groupBandVO.setPhoto(photo);
		groupBandVO.setGroupType(groupType);
		groupBandVO.setTotalAmout(totalAmout);
		groupBandVO.setStartTime(startTime);
		groupBandVO.setRate(rate);
		groupBandVO.setNote(note);
		groupBandVO.setGroupKind(groupKind);
		dao.insert(groupBandVO);
		return groupBandVO;
	}

	public GroupBandVO updateGroupBand(
			String groupID,Timestamp launchTime,
			String content, String introduction, Integer groupStatus, Integer currenTnum, Integer upperLimit,
			Integer lowerLimit, String groupName, String groupLeader, String startLoc, String endLoc, Integer privates,
			byte[] photo, String groupType, Integer totalAmout, Timestamp startTime, Integer rate, String note,Integer groupKind) {

		GroupBandVO groupBandVO = new GroupBandVO();
		groupBandVO.setGroupID(groupID);
		groupBandVO.setContent(content);
		groupBandVO.setLaunchTime(launchTime);
		groupBandVO.setIntroduction(introduction);
		groupBandVO.setGroupStatus(groupStatus);
		groupBandVO.setCurrenTnum(currenTnum);
		groupBandVO.setUpperLimit(upperLimit);
		groupBandVO.setLowerLimit(lowerLimit);
		groupBandVO.setGroupName(groupName);
		groupBandVO.setGroupLeader(groupLeader);
		groupBandVO.setStartLoc(startLoc);
		groupBandVO.setEndLoc(endLoc);
		groupBandVO.setPrivates(privates);
		groupBandVO.setPhoto(photo);
		groupBandVO.setGroupType(groupType);
		groupBandVO.setTotalAmout(totalAmout);
		groupBandVO.setStartTime(startTime);
		groupBandVO.setRate(rate);
		groupBandVO.setNote(note);
		groupBandVO.setGroupKind(groupKind);
		dao.update(groupBandVO);
		return groupBandVO;

	}
	
	public GroupBandVO getOneGroupBand(String GroupBandno) {
		return dao.findByPrimaryKey(GroupBandno);	
	}
	public List<GroupBandVO> getAll() {
		return dao.getAll();
		
	}
	
	public void deleteGroupBand(String empno) {
		dao.delete(empno);
	}
	public List<GroupBandVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public void UPDATE_GROUP_STATUS__GROUP_ID(Integer groupStatus,String groupID) {
		dao.UPDATE_GROUP_STATUS__GROUP_ID(groupStatus, groupID);
	}
	public void UPDATE_CONTENT__GROUP_ID(String groupID,String content ) {
		dao.UPDATE_CONTENT__GROUP_ID(groupID, content);		
	}
	public String getOneContentGroupID(String groupID) {
		return dao.getOneContentGroupID(groupID);
	}
	}
