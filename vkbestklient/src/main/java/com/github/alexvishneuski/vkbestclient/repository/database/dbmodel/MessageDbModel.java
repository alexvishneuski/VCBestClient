package com.github.alexvishneuski.vkbestclient.repository.database.dbmodel;

public class MessageDbModel {

    private int mId;
    private int mAuthor_id;
    private int mRecipient_id;
    private int mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

    public MessageDbModel(int pAuthor_id, int pRecipient_id, int pMessageSendingDate, String pMessageTitle, String pMessageBody, boolean pIsMessageRead) {
        mAuthor_id = pAuthor_id;
        mRecipient_id = pRecipient_id;
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
        return mAuthor_id;
    }

    public void setAuthor_id(int pAuthor_id) {
        mAuthor_id = pAuthor_id;
    }

    public int getRecipient_id() {
        return mRecipient_id;
    }

    public void setRecipient_id(int pRecipient_id) {
        mRecipient_id = pRecipient_id;
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
}
