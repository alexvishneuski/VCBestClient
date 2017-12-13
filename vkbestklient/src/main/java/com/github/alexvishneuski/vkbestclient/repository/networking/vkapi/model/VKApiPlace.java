package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Place
*/
public class VKApiPlace {

    // идентификатор места (если назначено)
    @SerializedName("id")
    private Integer mId;

    // название места (если назначено);
    @SerializedName("title")
    private Integer mTitle;

    // географическая широта;
    @SerializedName("latitude")
    private Double mLatitude;

    //  географическая долгота;
    @SerializedName("longitude")
    private Double mLongitude;

    // дата создания (если назначено);
    @SerializedName("created")
    private Integer mCreated;

    // URL изображения-иконки;
    @SerializedName("icon")
    private Integer mIconUrl;

    // название страны;
    @SerializedName("country")
    private Integer mCountry;

    // название города;
    @SerializedName("city")
    private Integer mCity;

    public VKApiPlace() {
    }

    public Integer getId() {

        return mId;
    }

    public void setId(final Integer pId) {
        mId = pId;
    }

    public Integer getTitle() {

        return mTitle;
    }

    public void setTitle(final Integer pTitle) {
        mTitle = pTitle;
    }

    public Double getLatitude() {

        return mLatitude;
    }

    public void setLatitude(final Double pLatitude) {
        mLatitude = pLatitude;
    }

    public Double getLongitude() {

        return mLongitude;
    }

    public void setLongitude(final Double pLongitude) {
        mLongitude = pLongitude;
    }

    public Integer getCreated() {

        return mCreated;
    }

    public void setCreated(final Integer pCreated) {
        mCreated = pCreated;
    }

    public Integer getIconUrl() {

        return mIconUrl;
    }

    public void setIconUrl(final Integer pIconUrl) {
        mIconUrl = pIconUrl;
    }

    public Integer getCountry() {

        return mCountry;
    }

    public void setCountry(final Integer pCountry) {
        mCountry = pCountry;
    }

    public Integer getCity() {

        return mCity;
    }

    public void setCity(final Integer pCity) {
        mCity = pCity;
    }
}
