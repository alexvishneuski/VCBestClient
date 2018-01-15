package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic;


import com.google.gson.annotations.SerializedName;

/**
 * model for VK API
 * object User
 * https://vk.com/dev/objects/user
 */
public class VKApiUser {

    /*basic fields*/

    @SerializedName("id")
    private Integer mId;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("deactivated")
    private String mDeactivatedFlag;

    @SerializedName("hidden")
    private Integer mHiddenFlag;


    /*optional fields*/

    @SerializedName("photo_50")
    private String mPhoto50;

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

    public String getDeactivatedFlag() {

        return mDeactivatedFlag;
    }

    public void setDeactivatedFlag(String pDeactivatedFlag) {
        mDeactivatedFlag = pDeactivatedFlag;
    }

    public Integer getHiddenFlag() {

        return mHiddenFlag;
    }

    public void setHiddenFlag(Integer pHiddenFlag) {
        mHiddenFlag = pHiddenFlag;
    }

    public String getPhoto50() {

        return mPhoto50;
    }

    public void setPhoto50(String pPhoto50) {
        mPhoto50 = pPhoto50;
    }

    @Override
    public String toString() {
        return "VKApiUser{" +
                "mId=" + mId +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mDeactivatedFlag='" + mDeactivatedFlag + '\'' +
                ", mHiddenFlag=" + mHiddenFlag +
                ", mPhoto50='" + mPhoto50 + '\'' +
                '}';
    }
}
