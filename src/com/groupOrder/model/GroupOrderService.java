package com.groupOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;

public class GroupOrderService {
	private GroupOrderDAO_interface dao;

	public GroupOrderService() {
		dao = new GroupOrderDAO();
	}

	public GroupOrderVO addGroupOrder(String driverID, String memID, Integer state, Integer totalAmout, Timestamp startTime,
			Timestamp endTime, Double startLng, Double startLat, Double endLng, Double endLat, Integer orderType,
			Integer rate, String note

	) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setDriverID(driverID);
		groupOrderVO.setMemID(memID);
		groupOrderVO.setState(state);
		groupOrderVO.setTotalAmout(totalAmout);
		groupOrderVO.setStartTime(startTime);
		groupOrderVO.setEndTime(endTime);
		groupOrderVO.setStartLng(startLng);
		groupOrderVO.setStartLat(startLat);
		groupOrderVO.setEndLng(endLng);
		groupOrderVO.setEndLat(endLat);
		groupOrderVO.setOrderType(orderType);
		groupOrderVO.setRate(rate);
		groupOrderVO.setNote(note);
		dao.insert(groupOrderVO);
		return groupOrderVO;
	}

	public GroupOrderVO updategroupOrderVO(String gorderID,String driverID, String memID, Integer state, Integer totalAmout,
			Timestamp startTime, Timestamp endTime, Double startLng, Double startLat, Double endLng, Double endLat,
			Integer orderType, Integer rate, String note) {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setGorderID(gorderID);
		groupOrderVO.setDriverID(driverID);
		groupOrderVO.setMemID(memID);
		groupOrderVO.setState(state);
		groupOrderVO.setTotalAmout(totalAmout);
		groupOrderVO.setStartTime(startTime);
		groupOrderVO.setEndTime(endTime);
		groupOrderVO.setStartLng(startLng);
		groupOrderVO.setStartLat(startLat);
		groupOrderVO.setEndLng(endLng);
		groupOrderVO.setEndLat(endLat);
		groupOrderVO.setOrderType(orderType);
		groupOrderVO.setRate(rate);
		groupOrderVO.setNote(note);
		dao.update(groupOrderVO);
		return groupOrderVO;
	}

	public GroupOrderVO getOneGroupOrder(String groupOrderno) {
		return dao.findByPrimaryKey(groupOrderno);
	}

	public List<GroupOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<GroupOrderVO> getByStateAndOrderType(Integer state, Integer orderType) {
	    return dao.getByStateAndOrderType(state, orderType);
	}
	
	public List<GroupOrderVO> getByStateAndDriverID(Integer state, String driverID) {
        return dao.getByStateAndDriverID(state, driverID);
    }
	
	public List<GroupOrderVO> GET_one_groupid__state_men_id(String memid) {		
		return dao.GET_ONE_groupid__state_men_id(memid);
	}	
	public Integer getOneDriversAve(String driver_id) {
		return dao.getOneDriversAve(driver_id);		
	}
	public void UPDATEmemid__GROUP_ID_MEM_ID(String groupid,String Memid) {
		 dao.UPDATEmemid__GROUP_ID_MEM_ID(groupid, Memid);
	}
	public String get_memid__memid_groupid(String memid,String groupid){
		return dao.get_memid__memid_groupid(memid,groupid);
		
	}
	
	//排成器
	public List<String> get_group_id__start_time(String START_TIME,String START_TIME2){
		return dao.get_group_id__start_time(START_TIME, START_TIME2);				
	}
	public Integer getMemID_groupID_startTime(String groupID,String START_TIME,String START_TIME2) {
		return dao.getMemID_groupID_startTime(groupID,START_TIME,START_TIME2);		
	}
	public void UPDATE_STATE__GROUP_ID(Integer STATE,String GROUP_ID) {
		dao.UPDATE_STATE__GROUP_ID(STATE, GROUP_ID);
		
	}
	public HashSet<String> getRatedDrivers(){
		return dao.getRatedDrivers();		
	}
	public void updateState_GroupID_mem_ID(String GROUP_ID,Integer State) {
		dao.updateState_GroupID_mem_ID(GROUP_ID, State);
	}
	public Integer getstateGrouID_Memid_Notnull(String groupID) {
		return dao.getstateGrouID_Memid_Notnull(groupID);
	}
	public void UPDATE_Total_AmoutGroupIDState(Integer TotalAmout,String GroupID,Integer State) {
		dao.UPDATE_Total_AmoutGroupIDState(TotalAmout, GroupID, State);
	}
	
	public void updateDriverIDByGroupID(String driverID, String groupID) {
	    System.out.println("fff");
	    dao.updateDriverIDByGroupID(driverID, groupID);
	}
	public Timestamp getStartTimeGgroupID(String groupID) {
	return 	dao.getStartTimeGgroupID(groupID);
	}
	
    //逾時訂單
    public List<String> getDelayOrder(){
    	return dao.getDelayOrder();
    }
    public List<GroupOrderVO> getALL_GroupID_State(String groupid,Integer state) {
    	return dao.getALL_GroupID_State(groupid, state);
    }
    public List<String> getMemID_groupID(String groupID) {
    		return dao.getMemID_groupID(groupID);
    	
    }
}
