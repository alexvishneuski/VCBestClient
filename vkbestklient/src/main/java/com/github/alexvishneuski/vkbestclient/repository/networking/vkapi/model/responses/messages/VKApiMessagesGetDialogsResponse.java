package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* model for response
* of VK API method: messages.getDialogs
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiMessagesGetDialogsResponse {

    @SerializedName("count")
    private Integer mDialogCount;

    @SerializedName("items")
    private List<VKApiDialog> mDialogs;

    public VKApiMessagesGetDialogsResponse() {
    }

    public Integer getDialogCount() {

        return mDialogCount;
    }

    public void setDialogCount(final Integer pDialogCount) {
        mDialogCount = pDialogCount;
    }

    public List<VKApiDialog> getDialogs() {

        return mDialogs;
    }

    public void setDialogs(final List<VKApiDialog> pDialogs) {
        mDialogs = pDialogs;
    }

    @Override
    public String toString() {
        return "VKApiMessagesGetDialogsResponse{" +
                "mDialogCount=" + mDialogCount +
                ", mDialogs=" + mDialogs +
                '}';
    }
}
