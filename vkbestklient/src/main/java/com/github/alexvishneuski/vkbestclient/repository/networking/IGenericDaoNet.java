package com.github.alexvishneuski.vkbestclient.repository.networking;

import java.util.List;

public interface IGenericDaoNet<T, C> {

    List<T> get(C mOffset, C mLimit);

    C getTotalCount();
}
