package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.VKApiMessagesGetDialogsResponse;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.VKApiMessagesGetDialogsResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.util.VKApiConstants;

/**
 *
 */
public class DialogVKApiNetworkingImpl implements IDialogVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public String getDialogListAsString() {

        Log.d(TAG, "getDialogListAsString called");

        //final String url = VKApiConstants.VK_API_SERVICE_URL + VKApiConstants.VK_API_METHOD_NAME + "?access_token=" + VKApiConstants.VK_API_ACCESS_TOKEN + "&v=" + VKApiConstants.VK_API_VERSION;
        final String url = String.format("%s%s?access_token=%s&v=%s", VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);

        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        final VKApiMessagesGetDialogsResult result = listener.getResult();

        //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
        if (result.getError() != null) {
            Log.d(TAG, "getDialogListAsString() returned Error");

            return result.getError();
        } else {
            Log.d(TAG, "getDialogListAsString() returned Response toString");

            return result.getResponse().toString();
        }
    }

    @WorkerThread

    public VKApiMessagesGetDialogsResponse getDialogList() {

        Log.d(TAG, "getDialogList called");

        //final String url = VKApiConstants.VK_API_SERVICE_URL + VKApiConstants.VK_API_METHOD_NAME + "?access_token=" + VKApiConstants.VK_API_ACCESS_TOKEN + "&v=" + VKApiConstants.VK_API_VERSION;
        final String url = String.format("%s%s?access_token=%s&v=%s", VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);

        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        final VKApiMessagesGetDialogsResult result = listener.getResult();

        //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
        if (result.getError() != null) {
            //Log.d(TAG, "getDialogList() returned Error");
            //return result.getError();
            throw new VKApiException();
        }
        Log.d(TAG, "getDialogList() returned Response");

        return result.getResponse();
    }

    public static class MyResponseListener implements HttpClient.ResponseListener {

        private static final String TAG = "MyResponseListener";

        private VKApiMessagesGetDialogsResult mResult;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mResult = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, VKApiMessagesGetDialogsResult.class);
            } catch (Exception e) {
                Log.d(TAG, "onResponse() called with: pInputStream = [" + pInputStream + "]");
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

        public VKApiMessagesGetDialogsResult getResult() {
            return mResult;
        }
    }

}
