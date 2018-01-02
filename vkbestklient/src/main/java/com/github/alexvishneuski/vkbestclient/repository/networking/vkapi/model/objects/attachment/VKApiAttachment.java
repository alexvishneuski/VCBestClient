package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.attachment;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Attachment
* https://vk.com/dev/objects/attachments_m
*/

public class VKApiAttachment {

   /* @SerializedName("type")
    private VKApiType mType;*/

    @SerializedName("type")
    private String mType;

    @SerializedName("photo")
    private VKApiPhoto mPhoto;

    @SerializedName("doc")
    private VKApiDocument mDocument;


    public String getType() {

        return mType;
    }

    public void setType(String pType) {
        mType = pType;
    }

    public VKApiPhoto getPhoto() {

        return mPhoto;
    }

    public void setPhoto(VKApiPhoto pPhoto) {
        mPhoto = pPhoto;
    }

    public VKApiDocument getDocument() {

        return mDocument;
    }

    public void setDocument(VKApiDocument pDocument) {
        mDocument = pDocument;
    }

    @Override
    public String toString() {
        return "VKApiAttachment{" +
                "mType='" + mType + '\'' +
                ", mPhoto=" + mPhoto +
                ", mDocument=" + mDocument +
                '}';
    }
}
