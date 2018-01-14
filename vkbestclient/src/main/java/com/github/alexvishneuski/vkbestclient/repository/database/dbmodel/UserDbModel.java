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

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof UserDbModel)) return false;

        UserDbModel that = (UserDbModel) pO;

        if (mId != that.mId) return false;
        if (mFirstName != null ? !mFirstName.equals(that.mFirstName) : that.mFirstName != null)
            return false;
        if (mLastName != null ? !mLastName.equals(that.mLastName) : that.mLastName != null)
            return false;
        return mAvatarPath != null ? mAvatarPath.equals(that.mAvatarPath) : that.mAvatarPath == null;
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
