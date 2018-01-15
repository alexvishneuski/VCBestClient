package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors.VKApiError;
import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* method: messages.getDialogs
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiMessagesGetDialogsResult {

    @SerializedName("response")
    private VKApiMessagesGetDialogsResponse mResponse;

    //TODO change to errors object
    @SerializedName("error")
    private VKApiError mError;

    public VKApiMessagesGetDialogsResult() {
    }

    public VKApiMessagesGetDialogsResponse getResponse() {

        return mResponse;
    }

    public void setResponse(VKApiMessagesGetDialogsResponse pResponse) {
        mResponse = pResponse;
    }

    public VKApiError getError() {
        return mError;
    }

    public void setError(VKApiError pError) {
        mError = pError;
    }

}
