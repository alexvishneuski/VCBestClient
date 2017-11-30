package com.github.alexvishneuski.vkbestclient.repo.networking.VKApiNetworking;


import com.github.alexvishneuski.vkbestclient.repo.networking.http.HttpClient;

import java.io.InputStream;

public class DialogNetworking {

    String request = "https://api.vk.com/method/messages.getDialogs?count=1&access_token=&v=5.69";

    public void getDialogs() {

        HttpClient client = new HttpClient();
        client.request(request, new HttpClient.ResponseListener() {
            @Override
            public void onResponse(InputStream pInputStream) throws Exception {

            }

            @Override
            public void onError(Throwable pThrowable) {

            }
        });


    }
}
