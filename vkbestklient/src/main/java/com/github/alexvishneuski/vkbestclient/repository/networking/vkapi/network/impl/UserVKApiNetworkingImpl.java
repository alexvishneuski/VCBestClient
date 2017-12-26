package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiRequestParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users.VKApiUsersGetResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetUsersParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class UserVKApiNetworkingImpl implements IUserVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiUser> getUsers() {

        final String methodsTag = "getUsers()";

        Log.d(TAG, "getUsers called");

        VKApiGetUsersParams usersParams = VKApiGetUsersParams.getBuilder().build();
        VKApiUri usersUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodUsersGet.METHOD_NAME)
                .setParameters(usersParams)
                .build();
        final String url = VKApiRequestParser.parse(usersUri);

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
