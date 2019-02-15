package com.groupBand.model;

import java.util.List;
import java.util.Set;




public interface GroupBandDAO_interface {
public void insert(GroupBandVO groupBandVO);
public void update(GroupBandVO groupBandVO);
public void delete(Integer groupBandno);
public GroupBandVO findByPrimaryKey(Integer groupBandno);
public List<GroupBandVO> getAll();
//public Set<> getEmpsByDeptno(Integer groupBandno);
}
