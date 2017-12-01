package com.github.alexvishneuski.vkbestclient.networking.VKApiNetworking;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.networking.vkapimodel.VKApiMessagesGetDialogsResult;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class DialogVKApiNetworking {

    @WorkerThread
    public String getDialogList() {

        final String url = "https://api.vk.com/method/messages.getDialogs?access_token=TOKEN&v=5.69";
        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new RuntimeException(listener.getThrowable());
        }
        VKApiMessagesGetDialogsResult result = listener.getResult();

        if (result.getError() != null) {
            return result.getError();
        } else {
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
