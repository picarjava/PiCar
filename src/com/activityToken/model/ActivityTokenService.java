package com.activityToken.model;

import java.util.List;

public class ActivityTokenService {
	private ActivityTokenDAO_Impl dao;
	
	public ActivityTokenService() {
		dao=new ActivityTokenJDBCDAO();
	}
	public ActivityTokenVO addActivityToken(String mem_id,String activity_id,Integer token_amount, java.sql.Date deadline) {
		ActivityTokenVO activityTokenVO=new ActivityTokenVO();
		activityTokenVO.setMem_id(mem_id);
		activityTokenVO.setActivity_id(activity_id);
		activityTokenVO.setToken_amount(token_amount);
		activityTokenVO.setDeadline(deadline);
		dao.insert(activityTokenVO);
		
		return activityTokenVO ;
	}
	
	public ActivityTokenVO updateActivityToken(String mem_id,String activity_id,Integer token_amount, java.sql.Date deadline) {
		ActivityTokenVO activityTokenVO=new ActivityTokenVO();
		activityTokenVO.setMem_id(mem_id);
		activityTokenVO.setActivity_id(activity_id);
		activityTokenVO.setToken_amount(token_amount);
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

}
