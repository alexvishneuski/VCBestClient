package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;

import java.util.List;
import java.util.Set;

public interface IUserInteractor {

    List<VKApiUser> getUsers();

    /**
     * @return List<VKApiUser>
     */
    List<VKApiUser> getUsers(List<Integer> pUserIds);

    /**
     * @return current VKApiUser
     */
    VKApiUser getCurrentUser();
}


