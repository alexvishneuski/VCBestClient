package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.dialogshistory;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* method: messages.getDialogs
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiMessagesGetHistoryResult {

    @SerializedName("response")
    private VKApiMessagesGetHistoryResponse mResponse;

    //TODO change to errors object
    @SerializedName("error")
    private String mError;

    public VKApiMessagesGetHistoryResult() {
    }

    public VKApiMessagesGetHistoryResponse getResponse() {

        return mResponse;
    }

    public void setResponse(VKApiMessagesGetHistoryResponse pResponse) {
        mResponse = pResponse;
    }

    public String getError() {

        return mError;
    }

    public void setError(String pError) {
        mError = pError;
    }
}
