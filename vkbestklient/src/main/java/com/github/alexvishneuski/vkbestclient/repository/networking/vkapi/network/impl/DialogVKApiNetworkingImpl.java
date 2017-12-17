package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResponse;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.util.VKApiConstants;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DialogVKApiNetworkingImpl implements IDialogVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiDialog> getDialogs() {

        final String methodsTag = "getDialogs()";

        Log.d(TAG, "getDialogs called");

        final String url = String.format(VKApiConstants.METHOD_BASE_PATH, VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);

        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        final VKApiMessagesGetDialogsResult result = listener.getResult();

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
            throw new VKApiException(errorMessage);
        }

        List<VKApiDialog> dialogs = new ArrayList<>();
        dialogs.addAll(result.getResponse().getDialogs());

        Log.d(TAG, "getDialogs returned dialogs");

        return dialogs;
    }


    @WorkerThread
    public VKApiMessagesGetDialogsResponse getDialogList() {

        final String methodsTag = "getDialogs()";

        Log.d(TAG, "getDialogs called");

        //final String url = VKApiConstants.VK_API_SERVICE_URL + VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS + "?access_token=" + VKApiConstants.VK_API_ACCESS_TOKEN + "&v=" + VKApiConstants.VK_API_VERSION;
        final String url = String.format(VKApiConstants.METHOD_BASE_PATH, VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);

        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        final VKApiMessagesGetDialogsResult result = listener.getResult();

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
            throw new VKApiException(errorMessage);
        }
        Log.d(TAG, "getDialogs() returned Response");

        return result.getResponse();
    }

    /*method used for testing if json is coming*/
    @WorkerThread
    public String getDialogListAsString() {

        Log.d(TAG, "getDialogListAsString called");

        //final String url = VKApiConstants.VK_API_SERVICE_URL + VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS + "?access_token=" + VKApiConstants.VK_API_ACCESS_TOKEN + "&v=" + VKApiConstants.VK_API_VERSION;
        final String url = String.format(VKApiConstants.METHOD_BASE_PATH, VKApiConstants.VK_API_SERVICE_URL, VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS, VKApiConstants.VK_API_ACCESS_TOKEN, VKApiConstants.VK_API_VERSION);

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

        public VKApiMessagesGetDialogsResult getResult() {
            return mResult;
        }
    }

}
