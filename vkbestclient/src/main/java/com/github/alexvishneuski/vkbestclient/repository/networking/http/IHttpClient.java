package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import java.io.InputStream;

public interface IHttpClient<T> {

    @Deprecated
    void requestGet(String pUrl, HttpClient.ResponseListener pListener);

    //What method is more prefered:  this
    T requestGet(String pUrl, Class<T> pClazz);

    //What method is more prefered:  or this with extracting parser in implementation
    InputStream requestGet(String pUrl);
}
