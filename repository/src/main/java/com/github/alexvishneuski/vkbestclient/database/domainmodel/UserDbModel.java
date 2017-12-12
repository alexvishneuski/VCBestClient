package com.github.alexvishneuski.vkbestclient.database.domainmodel;

public class UserDbModel {

    private Integer mId;
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

    public UserDbModel(Integer pId, String pFirstName, String pLastName,String pAvatarPath) {
        mId = pId;
        mFirstName = pFirstName;
        mLastName = pFirstName;
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
