package com.groupBand.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class GroupBandService {

	private GroupBandDAO_interface dao;

	public GroupBandService() {
		dao = new GroupBandDAO();
	}

	public GroupBandVO addGroupBand(String content, String introduction, Integer groupStatus, Integer currenTnum,
			Integer upperLimit, Integer lowerLimit, String groupName, String groupLeader, String startLoc,
			String endLoc, Integer privates, byte[] photo, String groupType, Integer totalAmout, Date startTime,
			Integer rate, String note) {

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

		dao.insert(groupBandVO);
		return groupBandVO;
	}

	public GroupBandVO updateGroupBand(

			String content, String introduction, Integer groupStatus, Integer currenTnum, Integer upperLimit,
			Integer lowerLimit, String groupName, String groupLeader, String startLoc, String endLoc, Integer privates,
			byte[] photo, String groupType, Integer totalAmout, Date startTime, Integer rate, String note) {

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

		dao.update(groupBandVO);
		return groupBandVO;

	}
	
	public GroupBandVO getOneGroupBand(String GroupBandno) {
		return dao.findByPrimaryKey(GroupBandno);	
	}
	public List<GroupBandVO> getAll() {
		return dao.getAll();
		
	}
	

}
