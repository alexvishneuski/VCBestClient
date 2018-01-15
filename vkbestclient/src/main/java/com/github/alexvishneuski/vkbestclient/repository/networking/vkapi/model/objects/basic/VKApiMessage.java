package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic;

/*
* model for VK API
* object Message
*/

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.additional.VKApiGeo;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.attachment.VKApiAttachment;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VKApiMessage {

    @SerializedName("id")
    private Integer mId;

    @SerializedName("user_id")
    private Integer mContactUserId;

    @SerializedName("from_id")
    private Integer mAuthorId;

    // дата отправки сообщения в формате Unixtime
    @SerializedName("date")
    private Integer mSendingDate;

    @SerializedName("read_state")
    private Integer mReadStatus;

    @SerializedName("out")
    private Integer mDirection;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("body")
    private String mBody;

    @SerializedName("geo")
    private VKApiGeo mGeo;

    @SerializedName("attachments")
    private List<VKApiAttachment> mAttachmentList;

    @SerializedName("fwd_messages")
    private List<VKApiMessage> mRedirectedMessageList;

    @SerializedName("emoji")
    private Integer mHasEmoji;

    @SerializedName("important")
    private Integer mIsImportant;

    @SerializedName("deleted")
    private Integer mIsDeleted;

    @SerializedName("random_id")
    private Integer mRandomId;

    public VKApiMessage() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(final Integer pId) {
        mId = pId;
    }

    public Integer getContactUserId() {

        return mContactUserId;
    }

    public void setContactUserId(final Integer pContactUserId) {
        mContactUserId = pContactUserId;
    }

    public Integer getAuthorId() {

        return mAuthorId;
    }

    public void setAuthorId(final Integer pAuthorId) {
        mAuthorId = pAuthorId;
    }

    public Integer getSendingDate() {

        return mSendingDate;
    }

    public void setSendingDate(final Integer pSendingDate) {
        mSendingDate = pSendingDate;
    }

    public Integer getReadStatus() {

        return mReadStatus;
    }

    public void setReadStatus(final Integer pReadStatus) {
        mReadStatus = pReadStatus;
    }

    public Integer getDirection() {

        return mDirection;
    }

    public void setDirection(final Integer pDirection) {
        mDirection = pDirection;
    }

    public String getTitle() {

        return mTitle;
    }

    public void setTitle(final String pTitle) {
        mTitle = pTitle;
    }

    public String getBody() {

        return mBody;
    }

    public void setBody(final String pBody) {
        mBody = pBody;
    }

    public VKApiGeo getGeo() {

        return mGeo;
    }

    public void setGeo(final VKApiGeo pGeo) {
        mGeo = pGeo;
    }

    public List<VKApiAttachment> getAttachmentList() {

        return mAttachmentList;
    }

    public void setAttachmentList(final List<VKApiAttachment> pAttachmentList) {
        mAttachmentList = pAttachmentList;
    }

    public List<VKApiMessage> getRedirectedMessageList() {

        return mRedirectedMessageList;
    }

    public void setRedirectedMessageList(final List<VKApiMessage> pRedirectedMessageList) {
        mRedirectedMessageList = pRedirectedMessageList;
    }

    public Integer getHasEmoji() {

        return mHasEmoji;
    }

    public void setHasEmoji(final Integer pHasEmoji) {
        mHasEmoji = pHasEmoji;
    }

    public Integer getIsImportant() {

        return mIsImportant;
    }

    public void setIsImportant(final Integer pIsImportant) {
        mIsImportant = pIsImportant;
    }

    public Integer getIsDeleted() {

        return mIsDeleted;
    }

    public void setIsDeleted(final Integer pIsDeleted) {
        mIsDeleted = pIsDeleted;
    }

    public Integer getRandomId() {

        return mRandomId;
    }

    public void setRandomId(final Integer pRandomId) {
        mRandomId = pRandomId;
    }

    @Override
    public String toString() {
        return "VKApiMessage{" +
                "mId=" + mId +
                ", mContactUserId=" + mContactUserId +
                ", mAuthorId=" + mAuthorId +
                ", mSendingDate=" + mSendingDate +
                ", mReadStatus=" + mReadStatus +
                ", mDirection=" + mDirection +
                ", mTitle='" + mTitle + '\'' +
                ", mBody='" + mBody + '\'' +
                ", mGeo=" + mGeo +
                ", mAttachmentList=" + mAttachmentList +
                ", mRedirectedMessageList=" + mRedirectedMessageList +
                ", mHasEmoji=" + mHasEmoji +
                ", mIsImportant=" + mIsImportant +
                ", mIsDeleted=" + mIsDeleted +
                ", mRandomId=" + mRandomId +
                '}';
    }
}

