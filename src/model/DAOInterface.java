package model;

import java.util.List;

public interface DAOInterface<T> {
    T findByPrimaryKey(T VO);
    void insert(T VO);
    void update(T VO);
    void delete(T VO);
    List<T> selectAll(T VO);
}
