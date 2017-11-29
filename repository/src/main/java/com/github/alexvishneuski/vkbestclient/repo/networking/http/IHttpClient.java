package com.github.alexvishneuski.vkbestclient.repo.networking.http;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);
}
