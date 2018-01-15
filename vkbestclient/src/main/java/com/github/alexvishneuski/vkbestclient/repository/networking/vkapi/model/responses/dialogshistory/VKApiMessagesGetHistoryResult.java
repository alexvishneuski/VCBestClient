package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.dialogshistory;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors.VKApiError;
import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* method: messages.getDialogs
* url: https://vk.com/dev/messages.getHistory
*/
public class VKApiMessagesGetHistoryResult {

    @SerializedName("response")
    private VKApiMessagesGetHistoryResponse mResponse;

    //TODO change to errors object
    @SerializedName("error")
    private VKApiError mError;

    public VKApiMessagesGetHistoryResult() {
    }

    public VKApiMessagesGetHistoryResponse getResponse() {

        return mResponse;
    }

    public void setResponse(VKApiMessagesGetHistoryResponse pResponse) {
        mResponse = pResponse;
    }

    public VKApiError getError() {
        return mError;
    }

    public void setError(VKApiError pError) {
        mError = pError;
    }
}
