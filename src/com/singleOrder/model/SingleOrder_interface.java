package com.singleOrder.model;

import java.util.List;

public interface SingleOrder_interface {
    SingleOrderVO findByPrimaryKey(String primaryKey);
    void insert(SingleOrderVO singleOrderVO);
    void update(SingleOrderVO singleOrderVO);
    List<SingleOrderVO> getAll();
    List<SingleOrderVO> getSingleOrdersByStateAndOrderType(Integer state, Integer orderType);
    void delete(String orderID);
} // interface SingleOrderInterface
