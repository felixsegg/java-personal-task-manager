package persistence.dao;

import java.util.Set;

public interface DAO<T> {
    boolean save(T entity);
    T findByID(Integer id);
    Set<T> findAll();
    boolean delete(T entity);
}
