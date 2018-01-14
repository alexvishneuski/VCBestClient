package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.dialogshistory;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* model for response
* of VK API method: messages.getHistory
* url: https://vk.com/dev.php?method=messages.getHistory
*/
public class VKApiMessagesGetHistoryResponse {

    @SerializedName("count")
    private Integer mMessagesCount;

    @SerializedName("unread")
    private Integer mUnreadCount;

    @SerializedName("items")
    private List<VKApiMessage> mMessages;

    public VKApiMessagesGetHistoryResponse() {
    }

    public Integer getMessagesCount() {
        return mMessagesCount;
    }

    public void setMessagesCount(Integer pMessagesCount) {
        mMessagesCount = pMessagesCount;
    }

    public Integer getUnreadCount() {
        return mUnreadCount;
    }

    public void setUnreadCount(Integer pUnreadCount) {
        mUnreadCount = pUnreadCount;
    }

    public List<VKApiMessage> getMessages() {
        return mMessages;
    }

    public void setMessages(List<VKApiMessage> pMessages) {
        mMessages = pMessages;
    }
}
