package com.github.alexvishneuski.vkbestclient.datamodel;


public class User {

    private int mUserId;
    private String mUserFirstName;
    private String mUserLastName;
    private String mUserAvatarPath;

    public User() {
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int pUserId) {
        mUserId = pUserId;
    }

    public String getUserFirstName() {
        return mUserFirstName;
    }

    public void setUserFirstName(String pUserFirstName) {
        mUserFirstName = pUserFirstName;
    }

    public String getUserLastName() {
        return mUserLastName;
    }

    public void setUserLastName(String pUserLastName) {
        mUserLastName = pUserLastName;
    }

    public String getUserAvatarPath() {
        return mUserAvatarPath;
    }

    public void setUserAvatarPath(String pUserAvatarPath) {
        mUserAvatarPath = pUserAvatarPath;
    }
}
