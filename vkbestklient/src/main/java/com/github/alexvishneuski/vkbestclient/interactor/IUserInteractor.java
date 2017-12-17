package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;

import java.util.List;

public interface IUserInteractor {

    /**
     * @return List<VKApiUser>
     */
    List<VKApiUser> getUsers();

    /**
     * @return current VKApiUser
     */
    VKApiUser getCurrentUser();
}


