package com.github.alexvishneuski.vkbestclient.repository.database;

import java.util.List;

public interface GenericDao<T, PK> {

    PK insert(T entity);

    PK bulkIsert(List<T> entities);

    T get(PK id);

    List<T> getAll();

    void update(T entity);

    void delete(PK id);

    boolean ifInDbExist(PK id);
}
