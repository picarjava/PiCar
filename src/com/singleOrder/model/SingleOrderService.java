package com.singleOrder.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SingleOrderService {
    private SingleOrder_interface singleOrderDAO;
    
    public SingleOrderService() {
        singleOrderDAO = new SingleOrderDAO();
    } // SingleOrderService()
    
    public SingleOrderVO getOneSingleOrder(String orderID) {
        return singleOrderDAO.findByPrimaryKey(orderID);
    } // getOneSingleOrder()
    
    /*小編預約訂單排程器用*/
    //撈單程訂單:三天前 可撈單程
	public HashSet<SingleOrderVO> listAllUnpaidReservationOrder(){
		return singleOrderDAO.listAllUnpaidReservationOrder();
	};
	//撈長期訂單1:三天前撈出長期單的前幾筆) 
	public HashSet<SingleOrderVO> listEarlierPartUnpaidFromLongtermSets(){
		return singleOrderDAO.listEarlierPartUnpaidFromLongtermSets();
	};
	//撈長期訂單2:再透過 撈長期第一筆LAUNCH_TIME，撈此長期預約的整組
	public HashSet<SingleOrderVO> listOneSetOfLongterm(Timestamp launchtime){
		return singleOrderDAO.listOneSetOfLongterm(launchtime);
	}; 
	   
    //小編新增找已評價司機
    public HashSet<String> getRatedDrivers(){
    	return singleOrderDAO.getRatedDrivers();
    }
    
    //小編新增長期insert方法
    public void insert(LinkedList<SingleOrderVO> singleOrderVOList) {
    	singleOrderDAO.insert(singleOrderVOList);
    }
    ////小編新增司機查評價平均
    public int findRateAveByDriverID(String driverID) {
    	return  singleOrderDAO.findRateAveByDriverID(driverID);
    }
        
    public SingleOrderVO addSingleOrder(String memID, Integer state, Timestamp startTime, String startLoc,
                                        String endLoc, Double startLng, Double startLat, Double endLng,
                                        Double endLat, Integer totalAmount, Integer orderType, String note,
                                        Timestamp lauchTime) {
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setMemID(memID);
        singleOrderVO.setState(state);
        singleOrderVO.setStartTime(startTime);
        singleOrderVO.setStartLoc(startLoc);
        singleOrderVO.setEndLoc(endLoc);
        singleOrderVO.setStartLng(startLng);
        singleOrderVO.setStartLat(startLat);
        singleOrderVO.setEndLng(endLng);
        singleOrderVO.setEndLat(endLat);
        singleOrderVO.setTotalAmount(totalAmount);
        singleOrderVO.setOrderType(orderType);
        singleOrderVO.setNote(note);
        singleOrderVO.setLaunchTime(lauchTime);
        singleOrderDAO.insert(singleOrderVO);
        return singleOrderVO;
    } // addSingleOrder()
    
    public void addSingleOrder(SingleOrderVO singleOrderVO) {
        singleOrderDAO.insert(singleOrderVO);
    } // addSingleOrder()
    
    public SingleOrderVO updateSingleOrder(String orderID, String driverID, Integer state, Timestamp startTime,
                                           Timestamp endTime, String startLoc, String endLoc, Double startLng,
                                           Double startLat, Double endLng, Double endLat, Integer totalAmount,
                                           Integer rate) {
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setOrderID(orderID);
        singleOrderVO.setDriverID(driverID);
        singleOrderVO.setState(state);
        singleOrderVO.setStartTime(startTime);
        singleOrderVO.setEndTime(endTime);
        singleOrderVO.setStartLoc(startLoc);
        singleOrderVO.setEndLoc(endLoc);
        singleOrderVO.setStartLng(startLng);
        singleOrderVO.setStartLat(startLat);
        singleOrderVO.setEndLng(endLng);
        singleOrderVO.setEndLat(endLat);
        singleOrderVO.setTotalAmount(totalAmount);
        singleOrderVO.setRate(rate);
        singleOrderDAO.update(singleOrderVO);
        return singleOrderVO;
    } // updateSingleOrder()
    
    //小編新增刪除方法
    public void delete(String orderID) {
    	singleOrderDAO.delete(orderID);
    } // delete()
  
    public List<SingleOrderVO> getAll() {
        return singleOrderDAO.getAll();
    } // getAll()
    
    public List<SingleOrderVO> getByStateAndOrderType(Integer state, Integer orderType) {
        return singleOrderDAO.findByStateAndOrderType(state, orderType);
    } // getByStateAndOrderType()
    
    public List<SingleOrderVO> getByStateAndDriverID(Integer state, String driverID) {
        return singleOrderDAO.findByStateAndDriverID(state, driverID);
    } //  getByStateAndDriverID()
    
    public void updateDriverIDAndStateByOrderID(String driverID, Integer state, String orderID) {
        singleOrderDAO.updateDriverIDAndStateByOrderID(driverID, state, orderID);
    } // updateDriverIDAndStateByOrderID()
    
    public void updateDriverIDAndStateByOrderID(String driverID, Integer state, List<String> orderIDs) {
        singleOrderDAO.updateDriverIDAndStateByOrderID(driverID, state, orderIDs);
    } // updateDriverIDAndStateByOrderID()
    
    //新增訂單管理延遲時間
    public void update_state_to_delay(){
    	singleOrderDAO.update_state_to_delay();
    } // update_state_to_delay()
    
  //逾時訂單1.
    public List<String> getDelayOrder(){
    	return singleOrderDAO.getDelayOrder();
    }
  //逾時訂單2.
    public void updateDelayOrder(String orderID){
    	 singleOrderDAO.updateOrderIDToDelay(orderID);
    }
    
    
} // class SingleOrderService
