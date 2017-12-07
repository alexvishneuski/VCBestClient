package com.github.alexvishneuski.vkbestclient.json;

import com.github.alexvishneuski.vkbestclient.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;

import org.junit.Test;

public class DialogVKApiNetworkingTest {

    DialogVKApiNetworkingImpl q;

    @Test
    public void dialogVKApiNetworkingTesting() {
        q = new DialogVKApiNetworkingImpl();

        String result = q.getDialogListAsString();
        System.out.println(result);
    }

}
