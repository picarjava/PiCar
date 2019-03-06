package com.singleOrder.model;

import java.util.LinkedList;
import java.util.List;

public interface SingleOrder_interface {
    SingleOrderVO findByPrimaryKey(String primaryKey);
    void insert(SingleOrderVO singleOrderVO);
    void update(SingleOrderVO singleOrderVO);
    void updateDriverIDAndStateByOrderID(String driverID, Integer state, String orderID);
    void updateDriverIDAndStateByOrderID(String driverID, Integer state, List<String> orderIDs);
    List<SingleOrderVO> getAll();
    List<SingleOrderVO> findByStateAndOrderType(Integer state, Integer orderType);
    void delete(String orderID);
    void insert(LinkedList<SingleOrderVO> singleOrderVOList); //長期訂單新增用
} // interface SingleOrderInterface
