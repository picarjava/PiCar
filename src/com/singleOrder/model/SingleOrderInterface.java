package com.singleOrder.model;

import java.util.List;

public interface SingleOrderInterface {
    SingleOrderVO findByPrimaryKey(String primaryKey);
    void insert(SingleOrderVO singleOrderVO);
    void update(SingleOrderVO singleOrderVO);
    List<SingleOrderVO> getAll();

} // interface SingleOrderInterface
