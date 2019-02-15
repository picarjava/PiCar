package com.groupMem.model;

import java.util.List;
import java.util.Set;




public interface GroupMemDAO_interface {
public void insert(GroupMemVO groupMemVO);
public void update(GroupMemVO groupMemVO);
public void delete(String groupMemno);
public GroupMemVO findByPrimaryKey(String groupMemno);
public List<GroupMemVO> getAll();
//public Set<> getEmpsByDeptno(Integer groupMemno);
}
