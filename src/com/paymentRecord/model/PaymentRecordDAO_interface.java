package com.paymentRecord.model;

import java.util.List;

public interface PaymentRecordDAO_interface {
	
	public void insert (PaymentRecordVO prVO);
	public void update (PaymentRecordVO prVO);
	public void delete (String paymentID);
	public PaymentRecordVO findByPrimartKey(String paymentID);
	public List<PaymentRecordVO> getAll();
	
	
}
