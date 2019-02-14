package com.location.model;

import java.util.List;

public interface LocationDAOInterface {

    InstantMessageVO findByPrimaryKey(String primaryKey1, String primaryKey2);
    void insert(InstantMessageVO VO);
    void update(InstantMessageVO VO, String location);
    void delete(String primaryKey1, String primaryKey2);
    List<InstantMessageVO> getAll();
}
