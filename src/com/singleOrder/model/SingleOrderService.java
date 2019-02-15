package com.singleOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class SingleOrderService {
    private SingleOrder_interface singleOrderDAO;
    
    public SingleOrderService() {
        singleOrderDAO = new SingleOrderDAO();
    } // SingleOrderService()
    
    public SingleOrderVO getOneSingleOrder(String orderId) {
        return singleOrderDAO.findByPrimaryKey(orderId);
    } // getOneSingleOrder()
    
    public SingleOrderVO addSingleOrder(String driverId, String memId, Integer state, Date startTime,
                                        Date endTime, String startLoc, String endLoc, Double startLng,
                                        Double startLat, Double endLng, Double endLat, Integer totalAmount,
                                        Integer orderType, Integer rate, String note, Timestamp lauchTime) {
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setDriverId(driverId);
        singleOrderVO.setMemId(memId);
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
        singleOrderVO.setOrderType(orderType);
        singleOrderVO.setRate(rate);
        singleOrderVO.setNote(note);
        singleOrderVO.setLauchTime(lauchTime);
        singleOrderDAO.insert(singleOrderVO);
        return singleOrderVO;
    } // addSingleOrder()
    
    public SingleOrderVO updateSingleOrder(String orderId,String driverId, String memId, Integer state, Date startTime,
                                           Date endTime, String startLoc, String endLoc, Double startLng,
                                           Double startLat, Double endLng, Double endLat, Integer totalAmount,
                                           Integer orderType, Integer rate, String note, Timestamp lauchTime) {
        SingleOrderVO singleOrderVO = new SingleOrderVO();
        singleOrderVO.setOrderId(orderId);
        singleOrderVO.setDriverId(driverId);
        singleOrderVO.setMemId(memId);
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
        singleOrderVO.setOrderType(orderType);
        singleOrderVO.setRate(rate);
        singleOrderVO.setNote(note);
        singleOrderVO.setLauchTime(lauchTime);
        singleOrderDAO.update(singleOrderVO);
        return singleOrderVO;
    } // updateSingleOrder()
    
    List<SingleOrderVO> getAll() {
        return singleOrderDAO.getAll();
    } // getAll()
} // class SingleOrderService
