package com.github.alexvishneuski.vkbestclient.repository.networking.http;

public interface IHttpClient<T> {

    void request(String url, HttpClient.ResponseListener listener);

    T request(String url, Class<T> clazz);
}
