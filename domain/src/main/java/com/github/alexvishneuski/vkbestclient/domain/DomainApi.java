package com.github.alexvishneuski.vkbestclient.domain;

public class DomainApi {

    private String mBasePath;

    public DomainApi(final String pBasePath) {
        if (pBasePath.charAt(pBasePath.length() - 1) == '/') {
            mBasePath = pBasePath;
        } else {
            mBasePath = pBasePath + "/";
        }
    }

    public String getLastAppVersionPath() {
        return mBasePath+"version";
    }


}
