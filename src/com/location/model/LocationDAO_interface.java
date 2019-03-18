package com.location.model;

import java.util.List;

public interface LocationDAO_interface {

    LocationVO findByPrimaryKey(String primaryKey1, String primaryKey2);
    void insert(LocationVO VO);
    void update(LocationVO VO, String location);
    void delete(String primaryKey1, String primaryKey2);
    List<LocationVO> getAll(String memID);
}
