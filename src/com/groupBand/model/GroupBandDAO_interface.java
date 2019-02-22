package com.groupBand.model;

import java.util.List;





public interface GroupBandDAO_interface {
public void insert(GroupBandVO groupBandVO);
public void update(GroupBandVO groupBandVO);
public void delete(String groupBandno);
public GroupBandVO findByPrimaryKey(String groupBandno);
public List<GroupBandVO> getAll();

}
