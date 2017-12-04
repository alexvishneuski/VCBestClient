package com.github.alexvishneuski.vkbestclient.networking.vkapi.model;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Attachment
* https://vk.com/dev/objects/attachments_m
*/
public class VKApiMediaAttachment {

   /* @SerializedName("type")
    private VKApiMediaAttachmentType mType;*/

    @SerializedName("type")
    private String mType;

    @SerializedName("photo")
    private VKApiMediaAttachmentTypePhoto mPhoto;

    public String getType() {

        return mType;
    }

    public void setType(String pType) {
        mType = pType;
    }

    public VKApiMediaAttachmentTypePhoto getPhoto() {

        return mPhoto;
    }

    public void setPhoto(VKApiMediaAttachmentTypePhoto pPhoto) {
        mPhoto = pPhoto;
    }

    @Override
    public String toString() {
        return "VKApiMediaAttachment{" +
                "mType='" + mType + '\'' +
                ", mPhoto=" + mPhoto +
                '}';
    }
}
