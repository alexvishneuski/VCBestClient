package com.github.alexvishneuski.vkbestclient.networking.vkapi.exception;

import android.util.Log;

public class VKApiException extends RuntimeException {

    private final String TAG = this.getClass().getSimpleName();

    private String mMessage;

    public VKApiException() {
    }

    public VKApiException(String pMessage) {
        Log.d(TAG, "VKApiException called");
        mMessage = pMessage;
    }

    @Override
    public String getMessage() {

        return mMessage;
    }
}
