package com.groupOrder.model;

import java.util.HashSet;
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
public String get_memid__memid_groupid(String memid,String groupid);

//排成器
public void UPDATE_STATE__GROUP_ID(Integer STATE,String GROUP_ID);
public List<String> get_group_id__start_time(String START_TIME,String START_TIME2);
public Integer getMemID_groupID_startTime(String groupID,String START_TIME,String START_TIME2);
HashSet<String> getRatedDrivers();
}
