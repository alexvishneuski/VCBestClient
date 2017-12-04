package com.github.alexvishneuski.vkbestclient.networking.vkapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
* model for VK API
* object Attachment
* https://vk.com/dev/objects/attachments_m
*/
public  class VKApiMediaAttachment {

    @SerializedName("type")
    private VKApiMediaAttachmentType mType;

private List<VKApiMediaAttachmentTypePhoto> mPhotoList;

}
