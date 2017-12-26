package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.UserVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetUsersParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class UserInteractorImpl implements IUserInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserVKApiNetworking mUserVKApiNet = new UserVKApiNetworkingImpl();

    @Override
    //TODO can be removed.
    public List<VKApiUser> getUsers() {
        Log.d(TAG, "getUsers: called");

        List<VKApiUser> users = new ArrayList<>();

        VKApiGetUsersParams usersParams = VKApiGetUsersParams.getBuilder().build();
        VKApiUri usersUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodUsersGet.METHOD_NAME)
                .setParameters(usersParams)
                .build();

        users.addAll(mUserVKApiNet.getUsers(usersUri));

        Log.d(TAG, "getUsers: returned users");

        return users;
    }

    @Override
    public VKApiUser getCurrentUser() {
        Log.d(TAG, "getCurrentUser: called");

        VKApiUser currentUser = this.getUsers().get(0);
        Log.d(TAG, "getCurrentUser: returned current user with " +
                "id: " + currentUser.getId()
                + ", FirstName:  " + currentUser.getFirstName()
                + ", LastName:  " + currentUser.getLastName()
                + ", AvatarPath:  " + currentUser.getPhoto50());
        return currentUser;
    }
}
