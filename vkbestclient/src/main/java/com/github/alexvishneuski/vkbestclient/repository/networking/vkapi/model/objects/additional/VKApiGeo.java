package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.additional;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Geo
*/
public class VKApiGeo {

    // тип места
    @SerializedName("type")
    private String mType;

    // координаты места
    @SerializedName("coordinates")
    private String mCoordinates;

    // описание места (если оно добавлено), объект с полями
    @SerializedName("place")
    private VKApiPlace mPlace;

    public VKApiGeo() {
    }

    public String getType() {

        return mType;
    }

    public void setType(String pType) {
        mType = pType;
    }

    public String getCoordinates() {

        return mCoordinates;
    }

    public void setCoordinates(String pCoordinates) {
        mCoordinates = pCoordinates;
    }

    public VKApiPlace getPlace() {

        return mPlace;
    }

    public void setPlace(VKApiPlace pPlace) {
        mPlace = pPlace;
    }
}
