package com.activity.model;

import java.sql.Date;
import java.util.List;
/*本類別方法類似DAO介面，差別在於(1)創建即創DAO實體(2)增與改另將VO值以參數傳入，再以DAO進行操作*/
public class ActivityService {
	private ActivityDAO_interface dao;
	
	/*創建本類別時，即創建一個DAO實體 用父介面型態的實體變數保存，目的是讓本類別中的所有方法可以用此DAO實體 以父介面的方法操作VO*/
	public ActivityService(){
		dao=new ActivityJNDIDAO();
	}
	
	public ActivityVO findActicvityByCode(String activityCode) {
		return dao.findActicvityByCode(activityCode);
	}
	
	/*相較於ActivityJDBCDAO中的Insert()，根據SQL常數，用vo.getxxx 去set pstmt需要的值*/
	/*此處寫一個方法，直接將 vo變數值 傳入參數，用vo. setxxx予以保存*/
	public ActivityVO addActivity(String activity_Name,String activity_Info,Date activity_Start,Date activity_End,String activity_Code,Integer token_Amount,byte[]activity_Post){
		ActivityVO activityVO=new ActivityVO();
		
		activityVO.setActivityName(activity_Name);
		activityVO.setActivityInfo(activity_Info);
		activityVO.setActivityStart(activity_Start);
		activityVO.setActivityEnd(activity_End);
		activityVO.setActivityCode(activity_Code);
		activityVO.setTokenAmount(token_Amount);
		activityVO.setActivityPost(activity_Post);
		dao.insert(activityVO);
		
		return activityVO;
	}
	//預留給 Struts 2 用的
	public void ActivityVO(ActivityVO activityVO){
		dao.insert(activityVO);
	}
	
	public ActivityVO updateActivity(String activity_Id,String activity_Name,String activity_Info,Date activity_Start,Date activity_End,String activity_Code,Integer token_Amount,byte[]activity_Post){
		ActivityVO activityVO=new ActivityVO();
		activityVO.setActivityID(activity_Id);
		activityVO.setActivityName(activity_Name);
		activityVO.setActivityInfo(activity_Info);
		activityVO.setActivityStart(activity_Start);
		activityVO.setActivityEnd(activity_End);
		activityVO.setActivityCode(activity_Code);
		activityVO.setTokenAmount(token_Amount);
		activityVO.setActivityPost(activity_Post);
		dao.update(activityVO);
		
		return dao.findByPrimaryKey(activity_Id);
	}
	
	//預留給 Struts 2 用的
	public void updateActivity(ActivityVO activityVO){
		dao.update(activityVO);
	}
	
	public void deleteActivity(String activity_Id){
		dao.delete(activity_Id);
	}
	public ActivityVO getOneActivity(String activity_Id){
		return dao.findByPrimaryKey(activity_Id);
	}
	
	public List<ActivityVO> getALL(){
		return dao.getAll();
	}
}
