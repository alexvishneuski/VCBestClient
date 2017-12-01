package com.github.alexvishneuski.vkbestclient.networking;

import com.github.alexvishneuski.vkbestclient.networking.VKApiNetworking.DialogVKApiNetworking;



public class DialogVKApiNetworkingTest {

    private static DialogVKApiNetworking q;

    public static void main(String[] args) {
        q = new DialogVKApiNetworking();

        String result = q.getDialogList();
        System.out.println(result);

    }



}
