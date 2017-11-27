package com.github.alexvishneuski.vkbestclient.imageloader;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface StreamProvider<T> {
    InputStream get(T path) throws IOException;
}
