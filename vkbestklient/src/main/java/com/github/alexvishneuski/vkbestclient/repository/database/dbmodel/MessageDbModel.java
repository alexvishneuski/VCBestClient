package com.github.alexvishneuski.vkbestclient.repository.database.dbmodel;

public class MessageDbModel {

    private long mId;
    private long mAuthor_id;
    private long mRecipient_id;
    private long mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

    public MessageDbModel(long pAuthor_id, long pRecipient_id, long pMessageSendingDate, String pMessageTitle, String pMessageBody, boolean pIsMessageRead) {
        mAuthor_id = pAuthor_id;
        mRecipient_id = pRecipient_id;
        mMessageSendingDate = pMessageSendingDate;
        mMessageTitle = pMessageTitle;
        mMessageBody = pMessageBody;
        mIsMessageRead = pIsMessageRead;
    }

    public long getId() {
        return mId;
    }

    public void setId(long pId) {
        mId = pId;
    }

    public long getAuthor_id() {
        return mAuthor_id;
    }

    public void setAuthor_id(long pAuthor_id) {
        mAuthor_id = pAuthor_id;
    }

    public long getRecipient_id() {
        return mRecipient_id;
    }

    public void setRecipient_id(long pRecipient_id) {
        mRecipient_id = pRecipient_id;
    }

    public long getMessageSendingDate() {
        return mMessageSendingDate;
    }

    public void setMessageSendingDate(long pMessageSendingDate) {
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
}
