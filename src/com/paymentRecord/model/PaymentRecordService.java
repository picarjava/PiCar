package com.paymentRecord.model;

import java.sql.Date;
import java.util.List;



public class PaymentRecordService {

	private PaymentRecordDAO_interface dao;

	public PaymentRecordService() {
		dao = new PaymentRecordDAO();
	}

	public PaymentRecordVO addPaymentRecord(String driverID, Integer payAmount) {

		PaymentRecordVO paymentRecordVO = new PaymentRecordVO();

		paymentRecordVO.setDriverID(driverID);
		paymentRecordVO.setPayAmount(payAmount);
//		paymentRecordVO.setPayTime(payTime); 日期自動產生 暫時註解

		dao.insert(paymentRecordVO);

		return paymentRecordVO;
	}

	public PaymentRecordVO updatePaymentRecord(String paymentID, String driverID, Integer payAmount, Date payTime) {

		PaymentRecordVO paymentRecordVO = new PaymentRecordVO();

		paymentRecordVO.setPaymentID(paymentID);
		paymentRecordVO.setDriverID(driverID);
		paymentRecordVO.setPayAmount(payAmount);
		paymentRecordVO.setPayTime(payTime);

		dao.update(paymentRecordVO);

		return paymentRecordVO;
	}

	public PaymentRecordVO getOnePaymentRecord(String paymentID) {
		return dao.findByPrimaryKey(paymentID);
	}
	
	public List<PaymentRecordVO> getDriverPaymentRecord(String driverID) {
		return dao.findByDriverID(driverID);
	}

	public void deletePaymentRecord(String paymentID) {
		dao.delete(paymentID);
	}

	public List<PaymentRecordVO> getAll() {
		return dao.getAll();
	}

}
