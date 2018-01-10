package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams;

/**
 * used to build request's part related to the method VK API messages.getHistory
 * https://vk.com/dev/messages.getHistory
 */
public class VKApiMessagesGetHistoryParams implements Parameters {

    private String mOffset;
    private String mCount;
    private String mUserId;
    private String mDestinationId;
    private String mStartMessageId;
    private String mInChronologicalOrder;

    private VKApiMessagesGetHistoryParams() {
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

    public String getUserId() {
        return mUserId;
    }

    public String getDestinationId() {
        return mDestinationId;
    }

    public String getInChronologicalOrder() {
        return mInChronologicalOrder;
    }

    public static Builder getBuilder() {

        return new VKApiMessagesGetHistoryParams().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setOffset(String pOffset) {
            VKApiMessagesGetHistoryParams.this.mOffset = pOffset;

            return this;
        }

        public Builder setCount(String pCount) {
            VKApiMessagesGetHistoryParams.this.mCount = pCount;

            return this;
        }

        public Builder setStartMessageId(String pStartMessageId) {
            VKApiMessagesGetHistoryParams.this.mStartMessageId = pStartMessageId;

            return this;
        }

        public Builder setUserId(String pUserId) {
            VKApiMessagesGetHistoryParams.this.mUserId = pUserId;

            return this;
        }

        public Builder setDestinationId(String pDestinationId) {
            VKApiMessagesGetHistoryParams.this.mDestinationId = pDestinationId;

            return this;
        }

        public Builder setInChronologicalOrder(String pInChronologicalOrder) {
            VKApiMessagesGetHistoryParams.this.mInChronologicalOrder = pInChronologicalOrder;

            return this;
        }

        public VKApiMessagesGetHistoryParams build() {

            return VKApiMessagesGetHistoryParams.this;
        }
    }
}
