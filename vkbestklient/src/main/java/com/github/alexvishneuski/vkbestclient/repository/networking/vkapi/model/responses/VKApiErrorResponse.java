package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors.VKApiError;
import com.google.gson.annotations.SerializedName;

public class VKApiErrorResponse {

    @SerializedName("error")
    private VKApiError mError;

    public VKApiError getError() {
        return mError;
    }

    public void setError(VKApiError pError) {
        mError = pError;
    }
}
