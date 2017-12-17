package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.UserVKApiNetworkingImpl;

import java.util.ArrayList;
import java.util.List;

public class UserInteractorImpl implements IUserInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserVKApiNetworking mUserVKApiNet = new UserVKApiNetworkingImpl();

    @Override
    public List<VKApiUser> getUsers() {
        Log.d(TAG, "getUsers: called");

        List<VKApiUser> users = new ArrayList<>();
        users.addAll(mUserVKApiNet.getUsers());

        Log.d(TAG, "getUsers: returned users");

        return users;
    }
}
