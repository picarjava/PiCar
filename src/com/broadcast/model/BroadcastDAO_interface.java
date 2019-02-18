package com.broadcast.model;

import java.util.List;

public interface BroadcastDAO_interface {
	
	
    public void insert(BroadcastVO broadcastVO);
    public void update(BroadcastVO broadcastVO);
    /*delete建構子argument是放欲刪除資料的PK*/
    public void delete(String msgID);
    public BroadcastVO findByPrimaryKey(Integer msgID);
    public List<BroadcastVO> getAll(); 
	
	


}
