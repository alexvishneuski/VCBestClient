package com.github.alexvishneuski.vkbestclient.networking.vkapimodel;

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
    private List<VKApiDialog> mDialogList;

    public VKApiMessagesGetDialogsResponse() {
    }

    public Integer getDialogCount() {

        return mDialogCount;
    }

    public void setDialogCount(final Integer pDialogCount) {
        mDialogCount = pDialogCount;
    }

    public List<VKApiDialog> getDialogList() {

        return mDialogList;
    }

    public void setDialogList(final List<VKApiDialog> pDialogList) {
        mDialogList = pDialogList;
    }
}
