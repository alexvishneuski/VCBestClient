package com.github.alexvishneuski.vkbestclient.domain;

import com.google.gson.annotations.SerializedName;

public class AppVersion {

    @SerializedName("id")
    private Integer mId;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer pId) {
        mId = pId;
    }


}
