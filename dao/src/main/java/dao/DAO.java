package dao;

import java.util.List;

public interface DAO<T> {
    void create(T entity);

    void update(T entity);

    T findById(String id);

    void delete(T entity);

    List<T> findAll();
}
