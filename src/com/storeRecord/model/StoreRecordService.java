package com.storeRecord.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class StoreRecordService {

	private StoreRecordDAO_interface dao;

	public StoreRecordService() {
		dao = new StoreRecordDAO();
	}

	public StoreRecordVO addStoreRecord(String memID, Integer amount) {

		StoreRecordVO storeRecordVO = new StoreRecordVO();

		storeRecordVO.setMemID(memID);
		storeRecordVO.setAmount(amount);
//		storeRecordVO.setPayTime(payTime); 日期自動產生 暫時註解

		dao.insert(storeRecordVO);

		return storeRecordVO;
	}

	public StoreRecordVO addOrdrID(String memID, Integer amount, String orderID) {

		StoreRecordVO storeRecordVO = new StoreRecordVO();

		storeRecordVO.setMemID(memID);
		storeRecordVO.setAmount(amount);
		storeRecordVO.setOrderID(orderID);

		dao.insertOrder(storeRecordVO);

		return storeRecordVO;
	}

//	public StoreRecordVO updateStoreRecord(String storeRecordID, String memID, Integer amount, Timestamp saveDate) {
//
//		StoreRecordVO storeRecordVO = new StoreRecordVO();
//
//		storeRecordVO.setStoreID(storeRecordID);
//		storeRecordVO.setMemID(memID);
//		storeRecordVO.setAmount(amount);
//		storeRecordVO.setSaveDate(saveDate);
//
//		dao.update(storeRecordVO);
//
//		return storeRecordVO;
//	}

	public StoreRecordVO getOneStoreRecord(String storeRecordID) {
		return dao.findByPrimaryKey(storeRecordID);
	}

	public List<StoreRecordVO> getMemStoreRecord(String memID) {
		return dao.findByMemID(memID);
	}

	public void deleteStoreRecord(String storeID) {
		dao.delete(storeID);
	}

	public List<StoreRecordVO> getAll() {
		return dao.getAll();
	}

//	public Integer getCount(String memID) {
//		return dao.getSumAmount(memID);
//	}

}
