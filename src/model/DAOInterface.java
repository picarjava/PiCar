package model;

import java.util.List;

public interface DAOInterface<T> {
    T findByPrimaryKey(String primaryKey);
    void insert(T VO);
    void update(T VO);
    void delete(String VO);
    List<T> getAll();
}
