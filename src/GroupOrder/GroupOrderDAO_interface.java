package GroupOrder;

import java.util.List;
import java.util.Set;




public interface GroupOrderDAO_interface {
public void insert(GroupOrderVO groupOrderVO);
public void update(GroupOrderVO groupOrderVO);
public void delete(Integer groupOrderno);
public GroupOrderVO findByPrimaryKey(Integer groupOrderno);
public List<GroupOrderVO> getAll();
//public Set<> getEmpsByDeptno(Integer groupOrderno);
}
