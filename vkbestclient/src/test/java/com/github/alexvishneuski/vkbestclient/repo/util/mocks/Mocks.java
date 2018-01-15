package com.github.alexvishneuski.vkbestclient.repo.util.mocks;

import junit.framework.Assert;

import java.io.InputStream;

public class Mocks {

    public static InputStream stream(final String pName) {
        final InputStream resourceAsStream = Mocks.class.getClassLoader().getResourceAsStream(pName);
        Assert.assertNotNull("resource not found, maybe you forget add .json?", resourceAsStream);
        return resourceAsStream;
    }
}
