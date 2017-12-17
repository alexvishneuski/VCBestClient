package com.github.alexvishneuski.vkbestclient.datamodel;

/**
 * from current user's view
 */
public class Message {

    private int mId;
    private int mCurrentUserId;
    private int mContactUserId;
    private MessageDirection mMessageDirection;
    private int mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public int getCurrentUserId() {
        return mCurrentUserId;
    }

    public void setCurrentUserId(int pCurrentUserId) {
        mCurrentUserId = pCurrentUserId;
    }

    public int getContactUserId() {
        return mContactUserId;
    }

    public void setContactUserId(int pContactUserId) {
        mContactUserId = pContactUserId;
    }

    public MessageDirection getMessageDirection() {
        return mMessageDirection;
    }

    public void setMessageDirection(MessageDirection pMessageDirection) {
        mMessageDirection = pMessageDirection;
    }

    public int getMessageSendingDate() {
        return mMessageSendingDate;
    }

    public void setMessageSendingDate(int pMessageSendingDate) {
        mMessageSendingDate = pMessageSendingDate;
    }

    public String getMessageTitle() {
        return mMessageTitle;
    }

    public void setMessageTitle(String pMessageTitle) {
        mMessageTitle = pMessageTitle;
    }

    public String getMessageBody() {
        return mMessageBody;
    }

    public void setMessageBody(String pMessageBody) {
        mMessageBody = pMessageBody;
    }

    public boolean isMessageRead() {
        return mIsMessageRead;
    }

    public void setMessageRead(boolean pMessageRead) {
        mIsMessageRead = pMessageRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mId=" + mId +
                ", mCurrentUserId=" + mCurrentUserId +
                ", mContactUserId=" + mContactUserId +
                ", mMessageDirection=" + mMessageDirection +
                ", mMessageSendingDate=" + mMessageSendingDate +
                ", mMessageTitle='" + mMessageTitle + '\'' +
                ", mMessageBody='" + mMessageBody + '\'' +
                ", mIsMessageRead=" + mIsMessageRead +
                '}';
    }
}
