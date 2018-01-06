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

    public MessageDbModel(int pAuthorId, int pRecipientId, int pMessageSendingDate, String pMessageTitle, String pMessageBody, int pIsMessageRead) {
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
}
