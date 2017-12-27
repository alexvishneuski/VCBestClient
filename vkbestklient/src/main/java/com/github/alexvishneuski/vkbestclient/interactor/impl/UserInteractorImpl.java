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
import java.util.Set;

public class UserInteractorImpl implements IUserInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserVKApiNetworking mUserVKApiNet = new UserVKApiNetworkingImpl();

    @Override
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
    public List<VKApiUser> getUsers(Set<Integer> pUserIds) {
        Log.d(TAG, "getUsers() called with: pUserIds = [" + pUserIds + "]");

        String[] array = new String[pUserIds.size()];
        for (int i = 0; i < pUserIds.size(); i++) {
            array[i] = String.valueOf(pUserIds.iterator().next());
        }

        List<VKApiUser> users = new ArrayList<>();

        VKApiGetUsersParams usersParams = VKApiGetUsersParams.getBuilder().setUserIds(array).build();
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
