package com.github.alexvishneuski.vkbestclient.presentation.model;

/**
 * Created by alex_vishneuski on 29.11.2017.
 */

public class UserInDialogViewModel {

    private Long mUserId;
    private String mUserFullNAme;
    private String mUserAvatarPath;

    public Long getUserId() {

        return mUserId;
    }

    public void setUserId(Long pUserId) {
        mUserId = pUserId;
    }

    public String getUserFullNAme() {

        return mUserFullNAme;
    }

    public void setUserFullNAme(String pUserFullNAme) {
        mUserFullNAme = pUserFullNAme;
    }

    public String getUserAvatarPath() {

        return mUserAvatarPath;
    }

    public void setUserAvatarPath(String pUserAvatarPath) {
        mUserAvatarPath = pUserAvatarPath;
    }
}
