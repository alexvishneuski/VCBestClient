package com.github.alexvishneuski.vkbestclient.repository.database.dbmodel;

public class MessageDbModel {

    private int mId;
    private int mAuthorId;
    private int mRecipientId;
    private int mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private int mIsMessageRead;

    public MessageDbModel() {
    }

    public MessageDbModel(int pId, int pAuthorId, int pRecipientId, int pMessageSendingDate, String pMessageTitle, String pMessageBody, int pIsMessageRead) {
        mId = pId;
        mAuthorId = pAuthorId;
        mRecipientId = pRecipientId;
        mMessageSendingDate = pMessageSendingDate;
        mMessageTitle = pMessageTitle;
        mMessageBody = pMessageBody;
        mIsMessageRead = pIsMessageRead;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public int getAuthor_id() {
        return mAuthorId;
    }

    public void setAuthor_id(int pAuthor_id) {
        mAuthorId = pAuthor_id;
    }

    public int getRecipient_id() {
        return mRecipientId;
    }

    public void setRecipient_id(int pRecipient_id) {
        mRecipientId = pRecipient_id;
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

    public int isMessageRead() {
        return mIsMessageRead;
    }

    public void setMessageRead(int pMessageRead) {
        mIsMessageRead = pMessageRead;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof MessageDbModel)) return false;

        MessageDbModel that = (MessageDbModel) pO;

        if (mId != that.mId) return false;
        if (mAuthorId != that.mAuthorId) return false;
        if (mRecipientId != that.mRecipientId) return false;
        if (mMessageSendingDate != that.mMessageSendingDate) return false;
        if (mIsMessageRead != that.mIsMessageRead) return false;
        if (mMessageTitle != null ? !mMessageTitle.equals(that.mMessageTitle) : that.mMessageTitle != null)
            return false;
        return mMessageBody != null ? mMessageBody.equals(that.mMessageBody) : that.mMessageBody == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mAuthorId;
        result = 31 * result + mRecipientId;
        result = 31 * result + mMessageSendingDate;
        result = 31 * result + (mMessageTitle != null ? mMessageTitle.hashCode() : 0);
        result = 31 * result + (mMessageBody != null ? mMessageBody.hashCode() : 0);
        result = 31 * result + mIsMessageRead;
        return result;
    }

    @Override
    public String toString() {
        return "MessageDbModel{" +
                "mId=" + mId +
                ", mAuthorId=" + mAuthorId +
                ", mRecipientId=" + mRecipientId +
                '}';
    }
}
