package com.singleOrder.model;

import java.util.List;

public interface SingleOrder_interface {
    SingleOrderVO findByPrimaryKey(String primaryKey);
    void insert(SingleOrderVO singleOrderVO);
    void update(SingleOrderVO singleOrderVO);
    List<SingleOrderVO> getAll();

} // interface SingleOrderInterface
