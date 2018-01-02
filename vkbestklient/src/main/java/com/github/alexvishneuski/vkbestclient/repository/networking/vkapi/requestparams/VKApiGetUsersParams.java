package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams;

/**
 * used to build request's part related to the method VK API users.get
 */
public class VKApiGetUsersParams implements Parameters {

    private String[] mUserIds;
    private String[] mFields;
    private String mNameCase;

    private VKApiGetUsersParams() {
    }

    public String[] getUserIds() {

        return mUserIds;
    }

    public String[] getFields() {

        return mFields;
    }

    public String getNameCase() {

        return mNameCase;
    }

    public static Builder getBuilder() {

        return new VKApiGetUsersParams().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setUserIds(String... pUserIds) {
            VKApiGetUsersParams.this.mUserIds = pUserIds;

            return this;
        }

        public Builder setFields(String... pFields) {
            VKApiGetUsersParams.this.mFields = pFields;

            return this;
        }

        public Builder setNameCase(String pNameCase) {
            VKApiGetUsersParams.this.mNameCase = pNameCase;

            return this;
        }

        public VKApiGetUsersParams build() {

            return VKApiGetUsersParams.this;
        }
    }
}
