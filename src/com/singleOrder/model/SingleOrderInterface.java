package com.singleOrder.model;

import java.util.List;

import model.DAOInterface;

public interface SingleOrderInterface extends DAOInterface<SingleOrderVO> {

    @Override
    default SingleOrderVO findByPrimaryKey(String primaryKey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    default void insert(SingleOrderVO VO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    default void update(SingleOrderVO VO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    default void delete(String VO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    default List<SingleOrderVO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
