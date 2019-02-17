package com.groupMem.model;

import java.util.List;



public class GroupMemService {
	private GroupMemDAO_interface dao;

	public GroupMemService() {
		dao = new GroupMemDAO();	
	}
	
	public GroupMemVO addGroupMem(
			 String groupID,
			String MemID,
			Integer state
			) {
		
		 GroupMemVO groupMemVO = new GroupMemVO();
			groupMemVO.setGroupID(groupID);
			groupMemVO.setMemID(MemID);
			groupMemVO.setState(state);
			dao.insert(groupMemVO);
		return groupMemVO;
	}
	
	public GroupMemVO updateGroupMem( String groupID,
			String MemID,
			Integer state) {
		 GroupMemVO groupMemVO = new GroupMemVO();
		
		 groupMemVO.setGroupID(groupID);
			groupMemVO.setMemID(MemID);
			groupMemVO.setState(state);
			dao.update(groupMemVO);
		return groupMemVO;
	}
	
	public GroupMemVO getOneGroupMem(String GroupMemno) {
		return dao.findByPrimaryKey(GroupMemno);
	}
	
	public List< GroupMemVO> getAll() {
		return dao.getAll();
	}
}
