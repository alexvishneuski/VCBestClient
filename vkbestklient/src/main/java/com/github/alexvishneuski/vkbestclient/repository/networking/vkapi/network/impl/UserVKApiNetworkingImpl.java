package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiRequestParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users.VKApiUsersGetResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;

import java.util.ArrayList;
import java.util.List;

public class UserVKApiNetworkingImpl implements IUserVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiUser> getUsers(VKApiUri pUri) {

        final String methodsTag = "getUsers()";

        Log.d(TAG, "getUsers called");

        final String url = VKApiRequestParser.parse(pUri);

        Log.d(TAG, "url called: " + url);

        @SuppressWarnings("unchecked") final VKApiUsersGetResult result =
                (VKApiUsersGetResult)
                        new HttpClient().requestGet(url, VKApiUsersGetResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
            throw new VKApiException(errorMessage);
        }

        List<VKApiUser> users = new ArrayList<>();
        users.addAll(result.getUsers());

        Log.d(TAG, "getUsers() returned " + users.size() + " users");

        return users;
    }
}
