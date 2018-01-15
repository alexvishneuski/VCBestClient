package com.github.alexvishneuski.vkbestclient.presentation.uimodel;

public class UserInDialogListViewModel {

    private int mUserId;
    private String mUserFullName;
    private String mUserAvatarPath;

    public UserInDialogListViewModel() {
    }

    public int getUserId() {

        return mUserId;
    }

    public void setUserId(int pUserId) {
        mUserId = pUserId;
    }

    public String getUserFullName() {

        return mUserFullName;
    }

    public void setUserFullName(String pUserFullName) {
        mUserFullName = pUserFullName;
    }

    public String getUserAvatarPath() {

        return mUserAvatarPath;
    }

    public void setUserAvatarPath(String pUserAvatarPath) {
        mUserAvatarPath = pUserAvatarPath;
    }
}
