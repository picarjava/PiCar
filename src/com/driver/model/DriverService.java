package com.driver.model;

import java.sql.Date;
import java.util.List;

//service 介於model && controller
public class DriverService {
	private DriverDAO_interface dao;

	// 創建DAO 實體保存在DAO介面的實體變數
	public DriverService() {
		dao = new DriverJNDIDAO();
	}

	// 比較JDBCDAO。利用多型 將VO變數傳入參數 用VO.set存
	public DriverVO addDriver(String driverID,String plateNum, 
			byte[] licence, byte[] criminal, byte[] trafficRecord, byte[] idNum,byte[] photo, 
			String carType, Integer sharedCar, Integer pet, Integer smoke, Integer babySeat) {
		DriverVO driverVO = new DriverVO();

		driverVO.setDriverID(driverID);
		driverVO.setPlateNum(plateNum);
		driverVO.setLicence(licence);
		driverVO.setCriminal(criminal);
		driverVO.setTrafficRecord(trafficRecord);
		driverVO.setIdNum(idNum);
		driverVO.setPhoto(photo);
				
		driverVO.setCarType(carType);
		driverVO.setSharedCar(sharedCar);
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);

		dao.insert(driverVO);
		return driverVO;
	}

//	//keep for Struts2
//	public void AddDriverVO(DriverVO driverVO) {
//		dao.insert(driverVO);
//	}

	public DriverVO updateDriver(String memID, String driverID, String plateNum, byte[] licence, byte[] criminal,
			byte[] trafficRecord, byte[] idNum, byte[] photo, Integer verified, Integer banned, Date deadline,
			Integer onlineCar, Integer score, String carType, Integer sharedCar, Integer pet, Integer smoke,
			Integer babySeat) {

		DriverVO driverVO = new DriverVO();
		driverVO.setMemID(memID);
		driverVO.setDriverID(driverID);
		driverVO.setPlateNum(plateNum);
		driverVO.setLicence(licence);
		driverVO.setCriminal(criminal);
		driverVO.setTrafficRecord(trafficRecord);
		driverVO.setIdNum(idNum);
		driverVO.setPhoto(photo);
		driverVO.setVerified(verified);
		driverVO.setBanned(banned);
		driverVO.setDeadline(deadline);
		driverVO.setOnlineCar(onlineCar);
		driverVO.setScore(score);
		driverVO.setCarType(carType);
		driverVO.setSharedCar(sharedCar);
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);
		dao.update(driverVO);
		return driverVO;

	}

//	//keep for Struts2
//	public void updateDriver(DriverVO driverVO) {
//		dao.update(driverVO);
//	}

	public List<DriverVO> getAll() {
		return dao.getAll();
	}

	public DriverVO getOneDriver(String driverID) {
		return dao.findByPrimaryKey(driverID);
	}

	public void deleteDriver(String driverID) {
		dao.delete(driverID);
	}

}
