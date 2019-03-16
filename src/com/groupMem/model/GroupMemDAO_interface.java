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
GroupMemVO findone_memid__ALL(String groupID, String memID, int state);
GroupMemVO findone_memid__GROUP_ID_MEM_ID(String groupID, String memID);
void update_State(String groupID, String memID, int state);
List<GroupMemVO> getAllgroupID(String GroupID,int state);
}
