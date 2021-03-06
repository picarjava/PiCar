package com.driver.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.member.model.MemberVO;

//service 介於model && controller
public class DriverService {
	private DriverDAO_interface dao;

	// 創建DAO 實體保存在DAO介面的實體變數
	public DriverService() {
		dao = new DriverJNDIDAO();
	}
	
	//小編更新司機評價
	public void updateDriverRate(int score,String driverID) {
		dao.updateDriverRate(score, driverID);
	} 

	// 比較JDBCDAO。利用多型 將VO變數傳入參數 用VO.set存
	public DriverVO addDriver(String memID, 
//			String driverID	,
			String plateNum, 
			byte[] licence, byte[] criminal, byte[] trafficRecord, byte[] idNum, 
			Integer verified,Integer banned,Date deadline,Integer onlineCar,Integer score,
			String carType, Integer sharedCar, Integer pet, Integer smoke, Integer babySeat) {
		DriverVO driverVO = new DriverVO();

		
		driverVO.setMemID(memID);
//		driverVO.setDriverID(driverID);
		driverVO.setPlateNum(plateNum);
		driverVO.setLicence(licence);
		driverVO.setCriminal(criminal);
		driverVO.setTrafficRecord(trafficRecord);
		driverVO.setIdNum(idNum);
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

		dao.insert(driverVO);
		return driverVO;
	}

//	//keep for Struts2
//	public void AddDriverVO(DriverVO driverVO) {
//		dao.insert(driverVO);
//	}

	public DriverVO updateDriver(String memID, String driverID, String plateNum, byte[] licence, byte[] criminal,
			byte[] trafficRecord, byte[] idNum, Integer verified, Integer banned, Date deadline,
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
	
	public DriverVO updateDriverdri(
//			String memID, String driverID, String plateNum, byte[] licence, byte[] criminal,
//			byte[] trafficRecord, byte[] idNum, Integer verified, Integer banned, Date deadline,
//			Integer onlineCar, Integer score, String carType, 
			Integer sharedCar, Integer pet, Integer smoke,	Integer babySeat) {

		DriverVO driverVO = new DriverVO();
//		driverVO.setMemID(memID);
//		driverVO.setDriverID(driverID);
//		driverVO.setPlateNum(plateNum);
//		driverVO.setLicence(licence);
//		driverVO.setCriminal(criminal);
//		driverVO.setTrafficRecord(trafficRecord);
//		driverVO.setIdNum(idNum);
//		driverVO.setVerified(verified);
//		driverVO.setBanned(banned);
//		driverVO.setDeadline(deadline);
//		driverVO.setOnlineCar(onlineCar);
//		driverVO.setScore(score);
//		driverVO.setCarType(carType);
		driverVO.setSharedCar(sharedCar);
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);
		dao.updatedri(driverVO);
		return driverVO;

	}
	
	//新增FOR前端設定喜好設定
	public DriverVO setHobby(Integer sharedcar, Integer pet, Integer smoke, Integer babySeat, String driverID) {

		DriverVO driverVO = new DriverVO();

		driverVO.setSharedCar(sharedcar);
		driverVO.setPet(pet);
		driverVO.setSmoke(smoke);
		driverVO.setBabySeat(babySeat);
		driverVO.setDriverID(driverID);
		dao.setForHobby(driverVO);

		return driverVO;
	}
	//新增FOR驗證司機
	public DriverVO setForVerified(Integer verified, String driverID) {
		
		DriverVO driverVO = new DriverVO();
		
		driverVO.setVerified(verified);
		driverVO.setDriverID(driverID);
		dao.setForPermit(driverVO);
		
		return driverVO;
	}

	public DriverVO updatePermitted(Integer verified, String driverID) {
		DriverVO driverVO = new DriverVO();
		driverVO.setDriverID(driverID);
		driverVO.setVerified(verified);
		dao.updatePermitted(driverVO);
		
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
	
	public DriverVO getOneDriverBymemID(String memID) {
	    return dao.findByMemID(memID);
	}
	public DriverVO getfindDriverByMemID(String memID) {
		return dao.findDriverByMemID(memID);
	}
	
	public DriverVO updateBanned(String driverID) {
		return dao.updateBanned(driverID);
	}
	public DriverVO updateBannedBack(String driverID) {
		return dao.updateBannedBack(driverID);
	}
	public DriverVO updateBannedtime(String oneday_after, String driverID) {
		return dao.updateBannedTime(oneday_after, driverID);
	}
}
