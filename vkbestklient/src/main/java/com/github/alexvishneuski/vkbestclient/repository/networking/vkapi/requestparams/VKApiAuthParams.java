package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams;


/**
 * used to build request's part related to authorization in VK API
 */
public class VKApiAuthParams implements Parameters {

    private String mClientId;
    private String mRedirectUri;
    private String mDisplay;
    private String[] mScope;
    private String mResponseType;
    private String mVersion;
    private String mState;
    private String mRevoke;

    private VKApiAuthParams() {
    }

    public String getClientId() {
        return mClientId;
    }

    public String getRedirectUri() {
        return mRedirectUri;
    }

    public String getDisplay() {
        return mDisplay;
    }

    public String[] getScope() {
        return mScope;
    }

    public String getResponseType() {
        return mResponseType;
    }

    public String getVersion() {
        return mVersion;
    }

    public String getState() {
        return mState;
    }

    public String getRevoke() {
        return mRevoke;
    }

    public static Builder getBuilder() {

        return new VKApiAuthParams().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setClientId(String pClientId) {
            VKApiAuthParams.this.mClientId = pClientId;

            return this;
        }

        public Builder setRedirectUri(String pRedirectUri) {
            VKApiAuthParams.this.mRedirectUri = pRedirectUri;

            return this;
        }

        public Builder setDisplay(String pDisplay) {
            VKApiAuthParams.this.mDisplay = pDisplay;

            return this;
        }

        public Builder setScope(String... pScope) {
            VKApiAuthParams.this.mScope = pScope;

            return this;
        }

        public Builder setResponseType(String pResponseType) {
            VKApiAuthParams.this.mResponseType = pResponseType;

            return this;
        }

        public Builder setState(String pState) {
            VKApiAuthParams.this.mState = pState;

            return this;
        }

        public Builder setVersion(String pVersion) {
            VKApiAuthParams.this.mVersion = pVersion;

            return this;
        }

        public Builder setRevoke(String pRevoke) {
            VKApiAuthParams.this.mRevoke = pRevoke;

            return this;
        }

        public VKApiAuthParams build() {

            return VKApiAuthParams.this;
        }

    }
}
