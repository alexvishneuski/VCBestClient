package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResponse;

import java.util.List;

public interface IDialogVKApiNetworking {

    List<VKApiDialog> getDialogs();

    VKApiMessagesGetDialogsResponse getDialogList();
}
