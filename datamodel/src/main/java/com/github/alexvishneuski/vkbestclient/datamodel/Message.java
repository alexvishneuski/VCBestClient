package com.github.alexvishneuski.vkbestclient.datamodel;

/**
 * from current user's view
 */

public class Message {

    private long mId;
    private long mCurrentUserId;
    private long mContactUserId;
    private MessageDirection mMessageDirection;
    private long mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

}
