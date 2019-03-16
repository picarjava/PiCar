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
	public  GroupMemVO findone_memid__ALL(String groupID,String memID,int state) {
		return dao.findone_memid__ALL(groupID, memID, state);
		
	}
	public  GroupMemVO findone_memid__GROUP_ID_MEM_ID(String groupID,String memID) {
		return dao.findone_memid__GROUP_ID_MEM_ID(groupID, memID);
		
	}
	public void update_State(String groupID, String memID, int state) {
		dao.update_State(groupID, memID, state);		
	}
}
