package com.github.alexvishneuski.vkbestclient.presentation.uimodel;

public class MessageInDialogListViewModel {

    private Long mId;
    private UserInDialogListViewModel mCurrentUser;
    private UserInDialogListViewModel mContactUser;
    private MessageDirectionViewModel mMessageDirection;
    private String mMessageSendingDate;
    private String mMessageBody;
    private boolean mIsMessageRead;

    public MessageInDialogListViewModel(UserInDialogListViewModel pCurrentUser, UserInDialogListViewModel pContactUser, String pMessageSendingDate, String pMessageBody, MessageDirectionViewModel pMessageDirection, boolean pIsMessageRead) {
        mCurrentUser = pCurrentUser;
        mContactUser = pContactUser;
        mMessageSendingDate = pMessageSendingDate;
        mMessageBody = pMessageBody;
        mMessageDirection = pMessageDirection;
        mIsMessageRead = pIsMessageRead;
    }

    public Long getId() {

        return mId;
    }

    public void setId(Long pId) {
        mId = pId;
    }

    public UserInDialogListViewModel getCurrentUser() {

        return mCurrentUser;
    }

    public UserInDialogListViewModel getContactUser() {

        return mContactUser;
    }

    public MessageDirectionViewModel getMessageDirection() {

        return mMessageDirection;
    }

    public String getMessageSendingDate() {
        return mMessageSendingDate;
    }

    public String getMessageBody() {

        return mMessageBody;
    }

    public boolean getMessageRead() {

        return mIsMessageRead;
    }
}
