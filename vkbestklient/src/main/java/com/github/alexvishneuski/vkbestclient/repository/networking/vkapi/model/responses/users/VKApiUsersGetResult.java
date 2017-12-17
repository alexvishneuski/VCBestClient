package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* method: users.get
* url: https://vk.com/dev/users.get
*/
public class VKApiUsersGetResult {

    @SerializedName("response")
    private VKApiUsersGetResponse mResponse;

    @SerializedName("error")
    private String mError;

    public VKApiUsersGetResult() {
    }

    public VKApiUsersGetResponse getResponse() {

        return mResponse;
    }

    public void setResponse(VKApiUsersGetResponse pResponse) {
        mResponse = pResponse;
    }

    public String getError() {

        return mError;
    }

    public void setError(String pError) {
        mError = pError;
    }

    @Override
    public String toString() {
        return "VKApiUsersGetResult{" +
                "mResponse=" + mResponse +
                ", mError='" + mError + '\'' +
                '}';
    }
}
