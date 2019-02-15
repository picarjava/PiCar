package com.instantMessage.model;

import java.sql.Date;
import java.util.List;

public interface InstantMessageInterface {
    InstantMessageVO findByPrimaryKey(String memId, Date startTime);
    void insert(InstantMessageVO VO);
    void update(InstantMessageVO VO);
    List<InstantMessageVO> getAll();
} // interface InstantMessageInterface
