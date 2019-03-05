package com.groupBand.model;

import java.util.*;

import com.groupOrder.model.GroupOrderVO;





public interface GroupBandDAO_interface {
public void insert(GroupBandVO groupBandVO);
public void update(GroupBandVO groupBandVO);
public void delete(String groupBandno);
public GroupBandVO findByPrimaryKey(String groupBandno);
public List<GroupBandVO> getAll();
public List<GroupBandVO> getAll(Map<String, String[]> map); 

public void insertWithEmps(GroupBandVO deptVO , List<GroupOrderVO> list);
}
