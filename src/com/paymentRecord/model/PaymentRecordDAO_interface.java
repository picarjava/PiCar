package com.paymentRecord.model;

import java.util.List;

public interface PaymentRecordDAO_interface {
	
	public void insert (PaymentRecordVO prVO);
	public void update (PaymentRecordVO prVO);
	public void delete (String paymentID);
	public PaymentRecordVO findByPrimaryKey(String paymentRecordID);
	public List<PaymentRecordVO> findByDriverID(String driverID);
	public List<PaymentRecordVO> getAll();
	public List<PaymentRecordVO> getDistinctDriverID();
	
	
}
