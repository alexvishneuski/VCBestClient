package repository.networking.vkapi.model.objects.basicobjects;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Dialog
*/
public class VKApiDialog {

    //unread — количество непрочитанных сообщений
    //(если все сообщения прочитаны, поле не возвращается
    @SerializedName("unread")
    private Integer mUnreadCount;

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
                ", mMessage=" + mMessage +
                ", mLastReadMessageByCurrentUserId=" + mLastReadMessageByCurrentUserId +
                ", mLastReadMessageByContactUserId=" + mLastReadMessageByContactUserId +
                '}';
    }
}


