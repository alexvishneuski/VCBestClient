package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.mediacontentandattachments;

import com.google.gson.annotations.SerializedName;

/*
* model for VK API
* object Attachment
* https://vk.com/dev/objects/attachments_m
*/
//TODO rename package and classes
public class VKApiMediaAttachment {

   /* @SerializedName("type")
    private VKApiMediaAttachmentType mType;*/

    @SerializedName("type")
    private String mType;

    @SerializedName("photo")
    private VKApiMediaAttachmentTypePhoto mPhoto;

    @SerializedName("doc")
    private VKApiMediaAttachmentTypeDocument mDocument;


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

    public VKApiMediaAttachmentTypeDocument getDocument() {

        return mDocument;
    }

    public void setDocument(VKApiMediaAttachmentTypeDocument pDocument) {
        mDocument = pDocument;
    }

    @Override
    public String toString() {
        return "VKApiMediaAttachment{" +
                "mType='" + mType + '\'' +
                ", mPhoto=" + mPhoto +
                ", mDocument=" + mDocument +
                '}';
    }
}
