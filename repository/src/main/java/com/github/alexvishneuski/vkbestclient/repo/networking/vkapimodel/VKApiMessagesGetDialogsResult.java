package com.github.alexvishneuski.vkbestclient.repo.networking.vkapimodel;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* method: messages.getDialogs
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiMessagesGetDialogsResult {

    @SerializedName("response")
    private VKApiMessagesGetDialogsResponse mResponse;

    @SerializedName("error")
    private String mError;

    public VKApiMessagesGetDialogsResult() {
    }

    public VKApiMessagesGetDialogsResponse getResponse() {
        return mResponse;
    }

    public void setResponse(VKApiMessagesGetDialogsResponse pResponse) {
        mResponse = pResponse;
    }

    public String getError() {
        return mError;
    }

    public void setError(String pError) {
        mError = pError;
    }
}
