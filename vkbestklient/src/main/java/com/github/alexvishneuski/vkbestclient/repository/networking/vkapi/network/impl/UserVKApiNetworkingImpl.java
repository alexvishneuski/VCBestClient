package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users.VKApiUsersGetResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class UserVKApiNetworkingImpl implements IUserVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiUser> getUsers() {

        final String methodsTag = "getUsers()";

        Log.d(TAG, "getUsers called");

        //TODO create some builder for api
        final String url = String.format(
                RepositoryConstants.VKApiConstants.METHOD_BASE_PATH,
                RepositoryConstants.VKApiConstants.VK_API_SERVICE_URL,
                RepositoryConstants.VKApiConstants.VK_API_METHOD_NAME_USERS_GET,
                RepositoryConstants.VKApiConstants.VK_API_ACCESS_TOKEN,
                RepositoryConstants.VKApiConstants.VK_API_VERSION);
        Log.d(TAG, "url called: " + url);

        @SuppressWarnings("unchecked") final VKApiUsersGetResult result =
                (VKApiUsersGetResult)
                        new HttpClient().request(url, VKApiUsersGetResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
            throw new VKApiException(errorMessage);
        }

        List<VKApiUser> users = new ArrayList<>();
        users.addAll(result.getUsers());

        Log.d(TAG, "getUsers() returned users");

        return users;
    }
}
