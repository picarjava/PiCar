package com.storeRecord.model;

import java.util.List;


public interface StoreRecordDAO_interface {

	public void insert(StoreRecordVO srVO);
	public void update(StoreRecordVO srVO);
	public void delete(String recordID);
	public StoreRecordVO findByPrimaryKey(String recordID);
	public List<StoreRecordVO> findByMemID(String memID);
	public List<StoreRecordVO> findByMemIDPos(String memID);
	public List<StoreRecordVO> findByMemIDNeg(String memID);
	
	public List<StoreRecordVO> getAll();
	public List<StoreRecordVO> getDistinctMemID();
	public void insertOrder(StoreRecordVO srVO);
	
	
	
	
	
//	public Integer getSumAmount(String memID);

}
