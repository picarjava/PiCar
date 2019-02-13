package GroupMem;

import java.util.List;
import java.util.Set;




public interface GroupMemDAO_interface {
public void insert(GroupMemVO groupMemVO);
public void update(GroupMemVO groupMemVO);
public void delete(Integer groupMemno);
public GroupMemVO findByPrimaryKey(Integer groupMemno);
public List<GroupMemVO> getAll();
//public Set<> getEmpsByDeptno(Integer groupMemno);
}
