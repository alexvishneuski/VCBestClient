package com.github.alexvishneuski.vkbestclient.repository.networking.http;

public interface IHttpClient<T> {

    void requestGet(String pUrl, HttpClient.ResponseListener pListener);

    T requestGet(String pUrl, Class<T> pClazz);

    T requestPost(String pUrl, Class<T> pClazz, String pBody);
}
