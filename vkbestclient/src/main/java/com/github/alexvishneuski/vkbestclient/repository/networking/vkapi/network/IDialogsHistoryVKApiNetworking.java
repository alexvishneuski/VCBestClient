package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;

import java.util.List;

public interface IDialogsHistoryVKApiNetworking {

    List<VKApiMessage> get(VKApiUri pUri);

    int getTotalCount(VKApiUri pUri);
}
