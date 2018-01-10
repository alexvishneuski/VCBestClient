package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import java.io.InputStream;

public interface IHttpClient<T> {

    @Deprecated
    void requestGet(String pUrl, HttpClient.ResponseListener pListener);

    T requestGet(String pUrl, Class<T> pClazz);

    T requestPost(String pUrl, Class<T> pClazz, String pBody);

    InputStream requestGet(String pUrl);
}
