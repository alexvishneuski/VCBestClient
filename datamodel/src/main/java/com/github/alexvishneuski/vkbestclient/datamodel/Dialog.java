package com.github.alexvishneuski.vkbestclient.datamodel;

import java.util.List;

/**
 * Created by Asus on 29.11.2017.
 */

public class Dialog {

    private Long mId;
    /*current app's user*/
    private User mCurrentUser;
    /*user in dialog with current user*/
    private User mContactUser;
    /*list of messages from one dialog*/
    private List<Message> mMessageList;

    public Long getId() {
        return mId;
    }

    public void setId(Long pId) {
        mId = pId;
    }

    public User getContactUser() {
        return mContactUser;
    }

    public void setContactUser(User pContactUser) {
        mContactUser = pContactUser;
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User pCurrentUser) {
        mCurrentUser = pCurrentUser;
    }

    public List<Message> getMessageList() {
        return mMessageList;
    }

    public void setMessageList(List<Message> pMessageList) {
        mMessageList = pMessageList;
    }
}
