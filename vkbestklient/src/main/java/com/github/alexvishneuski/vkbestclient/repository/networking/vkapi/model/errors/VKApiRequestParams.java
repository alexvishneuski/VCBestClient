package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors;

import com.google.gson.annotations.SerializedName;

public class VKApiRequestParams {

    @SerializedName("key")
    private String mKey;

    @SerializedName("value")
    private String mValue;

    public String getKey() {
        return mKey;
    }

    public void setKey(String pKey) {
        mKey = pKey;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String pValue) {
        mValue = pValue;
    }
}





