package com.github.alexvishneuski.vkbestclient.networking.http;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);
}
