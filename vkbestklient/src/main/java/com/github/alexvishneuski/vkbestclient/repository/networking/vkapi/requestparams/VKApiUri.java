package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams;

/**
 * used to build request's common part to VK API
 */
public class VKApiUri  {

    private String mProtocol;
    private String mBasePath;
    private String mMethod;
    private Parameters mParameters;

    private VKApiUri() {
    }

    public String getProtocol() {

        return mProtocol;
    }

    public String getBasePath() {

        return mBasePath;
    }

    public String getMethod() {

        return mMethod;
    }

    public Parameters getParameters() {

        return mParameters;
    }

    public static Builder getBuilder() {

        return new VKApiUri().new Builder();
    }

    public class Builder  {

        private Builder() {
        }


        public Builder setProtocol(String pProtocol) {
            VKApiUri.this.mProtocol = pProtocol;

            return this;
        }

        public Builder setBasePath(String pBasePath) {
            VKApiUri.this.mBasePath = pBasePath;

            return this;
        }

        public Builder setMethod(String pMethod) {
            VKApiUri.this.mMethod = pMethod;

            return this;
        }

        public Builder setParameters(Parameters pParameters) {
            VKApiUri.this.mParameters = pParameters;

            return this;
        }

        public VKApiUri build() {
            return VKApiUri.this;
        }

    }
}
