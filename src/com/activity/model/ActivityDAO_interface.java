package com.activity.model;

import java.util.List;

public interface ActivityDAO_interface {
	public void insert(ActivityVO activityVO);
	public void update(ActivityVO activityVO);
	/*delete建構子argument是放欲刪除資料的PK*/
	public void delete(String activity_id);
	public  ActivityVO findByPrimaryKey(String activity_id);
	public List<ActivityVO> getAll();
}
