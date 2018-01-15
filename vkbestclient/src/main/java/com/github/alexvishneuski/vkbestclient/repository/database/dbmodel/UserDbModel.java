package com.github.alexvishneuski.vkbestclient.repository.database.dbmodel;

public class UserDbModel {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mAvatarPath;

    public UserDbModel(int pId, String pFirstName, String pAvatarPath) {
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

    public String getLastName() {

        return mLastName;
    }

    public String getAvatarPath() {

        return mAvatarPath;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof UserDbModel)) return false;

        UserDbModel that = (UserDbModel) pO;

        return mId == that.mId && (mFirstName != null ? mFirstName.equals(that.mFirstName) : that.mFirstName == null) && (mLastName != null ? mLastName.equals(that.mLastName) : that.mLastName == null) && (mAvatarPath != null ? mAvatarPath.equals(that.mAvatarPath) : that.mAvatarPath == null);
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mFirstName != null ? mFirstName.hashCode() : 0);
        result = 31 * result + (mLastName != null ? mLastName.hashCode() : 0);
        result = 31 * result + (mAvatarPath != null ? mAvatarPath.hashCode() : 0);
        return result;
    }
}
