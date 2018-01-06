package com.github.alexvishneuski.vkbestclient.repository.database;

import java.util.List;

public interface GenericDao<T, PK>{

    PK insert(T entity);

    List<T> getAll();

    void update(T entity);

    void delete(PK id);

    T get(PK id);

    boolean ifInDbExist(PK id);

}
