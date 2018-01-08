package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKApiError {

    @SerializedName("error_code")
    private Integer mErrorCode;

    @SerializedName("error_msg")
    private String mErrorMessage;

    @SerializedName("request_params")
    private List<VKApiRequestParams> mRequestParams;

    public Integer getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(Integer pErrorCode) {
        mErrorCode = pErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String pErrorMessage) {
        mErrorMessage = pErrorMessage;
    }

    public List<VKApiRequestParams> getRequestParams() {
        return mRequestParams;
    }

    public void setRequestParams(List<VKApiRequestParams> pRequestParams) {
        mRequestParams = pRequestParams;
    }

    @Override
    public String toString() {
        return "VKApiError{" +
                "mErrorCode=" + mErrorCode +
                ", mErrorMessage='" + mErrorMessage + '\'' +
                ", mRequestParams=" + mRequestParams +
                '}';
    }
}


