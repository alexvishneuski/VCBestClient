package com.github.alexvishneuski.vkbestclient.database.model;

public class UserDbModel {

    private Integer mId;
    private String mFirstName;
    private String mAvatarPath;

    public UserDbModel() {
    }

    public UserDbModel(Integer pId, String pFirstName, String pAvatarPath) {
        mId = pId;
        mFirstName = pFirstName;
        mAvatarPath = pAvatarPath;
    }

    public Integer getId() {

        return mId;
    }

    public void setId(Integer pId) {
        mId = pId;
    }

    public String getFirstName() {

        return mFirstName;
    }

    public void setFirstName(String pFirstName) {

        mFirstName = pFirstName;
    }

    public String getAvatarPath() {
        return mAvatarPath;
    }

    public void setAvatarPath(String pAvatarPath) {
        mAvatarPath = pAvatarPath;
    }
}
