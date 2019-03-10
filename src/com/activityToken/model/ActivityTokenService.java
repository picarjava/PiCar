package com.activityToken.model;

import java.util.List;

public class ActivityTokenService {
	private ActivityTokenDAO_interface dao;
	
	 
	
	public ActivityTokenService() {
		dao=new ActivityTokenJNDIDAO();
	}
	
	public void update(ActivityTokenVO activityTokenVO, Integer sum) {
		dao.update(activityTokenVO,sum);
	}
	
	public List<ActivityTokenVO> findByMemID(String memID) {
		
		return dao.getOnesALL(memID);
	};
	
	public void addActivityToken(ActivityTokenVO activityTokenVO,Integer sum) {
		dao.insert(activityTokenVO, sum);
	};
	
	public ActivityTokenVO addActivityToken(String memID,String activityID,Integer tokenAmount, java.sql.Date deadline) {
		ActivityTokenVO activityTokenVO=new ActivityTokenVO();
		activityTokenVO.setMemID(memID);
		activityTokenVO.setActivityID(activityID);
		activityTokenVO.setTokenAmount(tokenAmount);
		activityTokenVO.setDeadline(deadline);
		dao.insert(activityTokenVO);
		
		return activityTokenVO ;
	}
	
	public ActivityTokenVO updateActivityToken(String mem_id,String activity_id,Integer token_amount, java.sql.Date deadline) {
		ActivityTokenVO activityTokenVO=new ActivityTokenVO();
		activityTokenVO.setMemID(mem_id);
		activityTokenVO.setActivityID(activity_id);
		activityTokenVO.setTokenAmount(token_amount);
		activityTokenVO.setDeadline(deadline);
		dao.update(activityTokenVO);
		
		return activityTokenVO ;
	}
	public ActivityTokenVO getOneActivityToken(String mem_id, String activity_id) {
		return dao.findByPrimaryKey(mem_id, activity_id);
		
	}
	
	public List<ActivityTokenVO> getAll(){
		return dao.getAll();
	}
	public List<ActivityTokenVO> getOnesALL(String memID) {
		return dao.getOnesALL(memID);
	}

}
