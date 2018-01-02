package com.github.alexvishneuski.vkbestclient.repository.database.dbmodel;

public class UserDbModel {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mAvatarPath;

    public UserDbModel(String pFirstName, String pLastName, String pAvatarPath) {
        mFirstName = pFirstName;
        mLastName = pLastName;
        mAvatarPath = pAvatarPath;
    }

    public UserDbModel() {
    }

    public UserDbModel(int pId, String pFirstName, String pLastName, String pAvatarPath) {
        mId = pId;
        mFirstName = pFirstName;
        mLastName = pFirstName;
        mAvatarPath = pAvatarPath;
    }

    public int getId() {

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

    public String getLastName() {

        return mLastName;
    }

    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    public String getAvatarPath() {

        return mAvatarPath;
    }

    public void setAvatarPath(String pAvatarPath) {
        mAvatarPath = pAvatarPath;
    }
}
