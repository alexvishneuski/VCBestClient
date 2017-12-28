package com.github.alexvishneuski.vkbestclient.interactor.model;

class UserInDialogs {

    private int mUserId;
    private String mUserFullName;
    private String mUserAvatarPath;

    public UserInDialogs() {
    }

    public UserInDialogs(int pUserId, String pUserFullName, String pUserAvatarPath) {
        mUserId = pUserId;
        mUserFullName = pUserFullName;
        mUserAvatarPath = pUserAvatarPath;
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


