package com.github.alexvishneuski.vkbestclient.repository.networking;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;

import java.util.List;

public interface IDialogsHistoryNet extends IGenericDaoNet<VKApiMessage, Integer> {

    List<VKApiMessage> getByUserId(Integer pOffset, Integer pLimit, Integer pUserId);

}
