package com.github.alexvishneuski.vkbestclient.json;

import com.github.alexvishneuski.vkbestclient.networking.VKApiNetworking.DialogVKApiNetworking;

import org.junit.Test;

public class DialogVKApiNetworkingTest {

    DialogVKApiNetworking q;

    @Test
    public void dialogVKApiNetworkingTesting() {
        q = new DialogVKApiNetworking();

        String result = q.getDialogList();
        System.out.println(result);
    }

}
