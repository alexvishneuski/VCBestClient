package com.github.alexvishneuski.vkbestclient.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.networking.vkapi.model.VKApiMessagesGetDialogsResponse;

public interface IDialogVKApiNetworking {


    String getDialogListAsString();


    VKApiMessagesGetDialogsResponse getDialogList();

}
