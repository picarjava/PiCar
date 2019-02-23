package com.singleOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class SingleOrderService {
    private SingleOrder_interface singleOrderDAO;
    
    public SingleOrderService() {
        singleOrderDAO = new SingleOrderDAO();
    } // SingleOrderService()
    
    public SingleOrderVO getOneSingleOrder(String orderID) {
        return singleOrderDAO.findByPrimaryKey(orderID);
    } // getOneSingleOrder()
        
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
    
    public List<SingleOrderVO> getAll() {
        return singleOrderDAO.getAll();
    } // getAll()

    
} // class SingleOrderService
