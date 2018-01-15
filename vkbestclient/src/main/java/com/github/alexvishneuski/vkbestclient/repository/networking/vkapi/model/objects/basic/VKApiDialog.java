package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Dialog
* url: https://vk.com/dev/messages.getDialogs
*/
public class VKApiDialog {

    //unread — количество непрочитанных сообщений
    //(если все сообщения прочитаны, поле не возвращается
    @SerializedName("unread")
    private Integer mUnreadCount;

    //Если был передан параметр start_message_id, будет также возвращено поле real_offset –
    // итоговое смещение данного подмножества диалогов
    // (оно может быть отрицательным, если был указан отрицательный offset).
    //not works this rule ('count' by 'start_message_id' specifying returns all dialog's count):
    //Если был передан параметр start_message_id, будет найдена позиция диалога в списке,
    // идентификатор последнего сообщения которого равен start_message_id
    // (или ближайший к нему более ранний). Начиная с этой позиции будет возвращено count диалогов.
    // Смещение offset в этом случае будет отсчитываться от этой позиции
    // (оно может быть отрицательным).
    @SerializedName("real_offset")
    private Integer mRealOffset;

    //message — объект, описывающий личное сообщение;
    @SerializedName("message")
    private VKApiMessage mMessage;

    //in_read — идентификатор последнего сообщения,прочитанного текущим пользователем;
    @SerializedName("in_read")
    private Integer mLastReadMessageByCurrentUserId;

    //out_read — идентификатор последнего сообщения,прочитанного собеседником.
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


