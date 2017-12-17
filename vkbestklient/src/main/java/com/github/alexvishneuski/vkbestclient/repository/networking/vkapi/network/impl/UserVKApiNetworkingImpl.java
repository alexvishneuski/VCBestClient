package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users.VKApiUsersGetResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.util.VKApiConstants;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserVKApiNetworkingImpl implements IUserVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiUser> getUsers() {

        final String methodsTag = "getUsers()";

        Log.d(TAG, "getUsers called");

        final String url = String.format(VKApiConstants.METHOD_BASE_PATH, VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME_USERS_GET, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);
        Log.d(TAG, "url called: " + url);

        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        final VKApiUsersGetResult result = listener.getResult();

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
            throw new VKApiException(errorMessage);
        }

        List<VKApiUser> users = new ArrayList<>();
        users.addAll(result.getUsers());

        Log.d(TAG, "getUsers() returned users");

        return users;
    }


    public static class MyResponseListener implements HttpClient.ResponseListener {

        private static final String TAG = "MyResponseListener";

        private VKApiUsersGetResult mResult;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mResult = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, VKApiUsersGetResult.class);
                //http exception
            } catch (Exception e) {
                Log.e(TAG, "onResponse() called, has got pInputStream = [" + pInputStream + "]");
                mThrowable = e;
            } finally {

                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        public VKApiUsersGetResult getResult() {
            return mResult;
        }
    }

}
