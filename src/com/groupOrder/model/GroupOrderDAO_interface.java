package com.groupOrder.model;

import java.util.List;
import java.util.Set;
public interface GroupOrderDAO_interface {
public void insert(GroupOrderVO groupOrderVO);
public void update(GroupOrderVO groupOrderVO);
public void delete(String groupOrderno);
public GroupOrderVO findByPrimaryKey(String groupOrderno);
public Integer getOneDriversAve(String driver_id);
public List<GroupOrderVO> getAll();
//public Set<> getEmpsByDeptno(Integer groupOrderno);
public void insert2 (List<GroupOrderVO> list , java.sql.Connection con);
public List<GroupOrderVO> GET_ONE_groupid__state_men_id(String memid);
public void UPDATEmemid__GROUP_ID_MEM_ID(String GROUP_ID,String Memid);
}
