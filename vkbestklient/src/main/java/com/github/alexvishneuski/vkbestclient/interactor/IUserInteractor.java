package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;

import java.util.List;

public interface IUserInteractor {


    /**
     * @return List<VKApiUser>
     */
    List<UserInDialogs> getDomainUsers(List<Integer> pUserIds);

    UserInDialogs getCurrentUserDomain();

    /**
     * @return current VKApiUser
     */

    List<VKApiUser> getUsers();


}


