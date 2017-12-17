package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* model for response
* of VK API method: users.get
* url: https://vk.com/dev/users.get
*/
public class VKApiUsersGetResponse {

    @SerializedName("items")
    private List<VKApiUser> mUsers;

    public VKApiUsersGetResponse() {
    }

    public List<VKApiUser> getUsers() {

        return mUsers;
    }

    public void setUsers(List<VKApiUser> pUsers) {
        mUsers = pUsers;
    }

    @Override
    public String toString() {
        return "VKApiUsersGetResponse{" +
                "mUsers=" + mUsers +
                '}';
    }
}
