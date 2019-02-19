package com.activityToken.model;

import java.util.List;



public interface ActivityTokenDAO_interface {
	
	public void insert(ActivityTokenVO activityTokenVO);
    public void update(ActivityTokenVO activityTokenVO);
    public void delete(String mem_id,String activity_id);
    public ActivityTokenVO findByPrimaryKey(String mem_id,String activity_id);
    public List<ActivityTokenVO> getAll();

}
