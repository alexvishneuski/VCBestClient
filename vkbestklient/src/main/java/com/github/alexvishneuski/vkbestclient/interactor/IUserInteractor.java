package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiUser;

import java.util.List;

public interface IUserInteractor {


    /**
     * @return List<VKApiUser>
     */
    List<UserInDialogs> getDomainUsersBasicInfo(List<Integer> pUserIds);

    //TODO to use in User about activity
    List<UserInDialogs> getDomainUsersFullInfo(List<Integer> pUserIds);

    UserInDialogs getCurrentUserDomain();

    /**
     * @return current VKApiUser
     */

    List<VKApiUser> getUsers();


}


