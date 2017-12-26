package com.github.alexvishneuski.vkbestclient.repository.networking.utils;

public class VKApiGetDialogsParams implements Parameters  {

    private String mOffset;
    private String mCount;
    private String mStartMessageId;
    private String mPreviewLength;
    private String mUnreadFlag;
    private String mImportantFlag;
    private String mUnansweredFlag;

    private VKApiGetDialogsParams() {
    }

    public String getOffset() {
        return mOffset;
    }

    public String getCount() {
        return mCount;
    }

    public String getStartMessageId() {
        return mStartMessageId;
    }

    public String getPreviewLength() {
        return mPreviewLength;
    }

    public String getUnreadFlag() {
        return mUnreadFlag;
    }

    public String getImportantFlag() {
        return mImportantFlag;
    }

    public String getUnansweredFlag() {
        return mUnansweredFlag;
    }

    public static Builder getBuilder() {

        return new VKApiGetDialogsParams().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setOffset(String pOffset) {
            VKApiGetDialogsParams.this.mOffset = pOffset;

            return this;
        }

        public Builder setCount(String pCount) {
            VKApiGetDialogsParams.this.mCount = pCount;

            return this;
        }

        public Builder setStartMessageId(String pStartMessageId) {
            VKApiGetDialogsParams.this.mStartMessageId = pStartMessageId;

            return this;
        }

        public Builder setPreviewLength(String pPreviewLength) {
            VKApiGetDialogsParams.this.mPreviewLength = pPreviewLength;

            return this;
        }

        public Builder setUnreadFlag(String pUnreadFlag) {
            VKApiGetDialogsParams.this.mUnreadFlag = pUnreadFlag;

            return this;
        }

        public Builder setImportantFlag(String pImportantFlag) {
            VKApiGetDialogsParams.this.mImportantFlag = pImportantFlag;

            return this;
        }

        public Builder setUnansweredFlag(String pUnansweredFlag) {
            VKApiGetDialogsParams.this.mUnansweredFlag = pUnansweredFlag;

            return this;
        }


        public VKApiGetDialogsParams build() {

            return VKApiGetDialogsParams.this;
        }

    }
}
