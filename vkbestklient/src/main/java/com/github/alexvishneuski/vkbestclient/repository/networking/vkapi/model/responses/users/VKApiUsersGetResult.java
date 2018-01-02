package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* model for VK API
* method: users.get
* url: https://vk.com/dev/users.get
*/
public class VKApiUsersGetResult {

    /*@SerializedName("response")
    private VKApiUsersGetResponse mResponse;
*/
    @SerializedName("response")
    private List<VKApiUser> mUsers;

    @SerializedName("error")
    private String mError;

    public VKApiUsersGetResult() {
    }

    /*public VKApiUsersGetResponse getResponse() {

        return mResponse;
    }

    public void setResponse(VKApiUsersGetResponse pResponse) {
        mResponse = pResponse;
    }
*/

    public List<VKApiUser> getUsers() {
        return mUsers;
    }

    public void setUsers(List<VKApiUser> pUsers) {
        mUsers = pUsers;
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
                "mUsers=" + mUsers +
                ", mError='" + mError + '\'' +
                '}';
    }
}
