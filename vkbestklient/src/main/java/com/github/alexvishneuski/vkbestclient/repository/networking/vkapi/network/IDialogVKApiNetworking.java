package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResponse;

public interface IDialogVKApiNetworking {


    String getDialogListAsString();


    VKApiMessagesGetDialogsResponse getDialogList();

}
