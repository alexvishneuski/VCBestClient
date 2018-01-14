package com.github.alexvishneuski.vkbestclient.repository.database;

import java.util.List;

public interface GenericDao<T, PK> {

    PK insert(T pEntity);

    PK bulkIsert(List<T> pEntities);

    T get(PK pId);

    List<T> getAll();

    List<T> getLimitedPartWithOffset(PK pOffset, PK pLimit);

    void update(T pEntity);

    void delete(PK pId);

    boolean ifInDbExist(PK pId);


}
