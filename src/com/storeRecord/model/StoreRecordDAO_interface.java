package com.storeRecord.model;

import java.util.List;

public interface StoreRecordDAO_interface {

	public void insert(StoreRecordVO srVO);
	public void update(StoreRecordVO srVO);
	public void delete(String recordID);
	public StoreRecordVO findByPrimaryKey(String recordID);
	public List<StoreRecordVO> getAll();

}