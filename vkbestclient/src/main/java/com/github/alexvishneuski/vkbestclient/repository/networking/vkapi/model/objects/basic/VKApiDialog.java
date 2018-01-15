package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Dialog
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiDialog {


    @SerializedName("unread")
    private Integer mUnreadCount;

    @SerializedName("real_offset")
    private Integer mRealOffset;


    @SerializedName("message")
    private VKApiMessage mMessage;

    @SerializedName("in_read")
    private Integer mLastReadMessageByCurrentUserId;

    @SerializedName("out_read")
    private Integer mLastReadMessageByContactUserId;

    public VKApiDialog() {
    }

    public Integer getUnreadCount() {

        return mUnreadCount;
    }

    public void setUnreadCount(final Integer pUnreadCount) {
        mUnreadCount = pUnreadCount;
    }

    public Integer getRealOffset() {

        return mRealOffset;
    }

    public void setRealOffset(Integer pRealOffset) {
        mRealOffset = pRealOffset;
    }

    public VKApiMessage getMessage() {

        return mMessage;
    }

    public void setMessage(final VKApiMessage pMessage) {
        mMessage = pMessage;
    }

    public Integer getLastReadMessageByCurrentUserId() {

        return mLastReadMessageByCurrentUserId;
    }

    public void setLastReadMessageByCurrentUserId(final Integer pLastReadMessageByCurrentUserId) {
        mLastReadMessageByCurrentUserId = pLastReadMessageByCurrentUserId;
    }

    public Integer getLastReadMessageByContactUserId() {

        return mLastReadMessageByContactUserId;
    }

    public void setLastReadMessageByContactUserId(final Integer pLastReadMessageByContactUserId) {
        mLastReadMessageByContactUserId = pLastReadMessageByContactUserId;
    }

    @Override
    public String toString() {
        return "VKApiDialog{" +
                "mUnreadCount=" + mUnreadCount +
                ", mRealOffset=" + mRealOffset +
                ", mMessage=" + mMessage +
                ", mLastReadMessageByCurrentUserId=" + mLastReadMessageByCurrentUserId +
                ", mLastReadMessageByContactUserId=" + mLastReadMessageByContactUserId +
                '}';
    }
}


