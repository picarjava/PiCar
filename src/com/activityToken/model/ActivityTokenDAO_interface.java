package com.activityToken.model;

import java.util.List;



public interface ActivityTokenDAO_interface {
	
	public void insert(ActivityTokenVO activityTokenVO);
    public void update(ActivityTokenVO activityTokenVO);
    public void delete(String mem_id,String activity_id);
    public ActivityTokenVO findByPrimaryKey(String mem_id,String activity_id);
    public List<ActivityTokenVO> getAll();
    /*新增單一會員查詢所有代幣明細的方法*/
    public List<ActivityTokenVO> getOnesALL(String memID);
    /*處理交易問題-使用活動代幣的方法*/
    public void update(ActivityTokenVO activityTokenVO, Integer sum);
    /*處理交易問題-領取活動代幣的方法*/
    public void insert(ActivityTokenVO activityTokenVO,Integer sum);

}
