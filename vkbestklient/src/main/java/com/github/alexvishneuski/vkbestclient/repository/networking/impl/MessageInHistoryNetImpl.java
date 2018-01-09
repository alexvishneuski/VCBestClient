package com.github.alexvishneuski.vkbestclient.repository.networking.impl;

import com.github.alexvishneuski.vkbestclient.repository.networking.IMessageInHistoryNet;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;

import java.util.List;


public class MessageInHistoryNetImpl implements IMessageInHistoryNet {

    @Override
    public List<VKApiMessage> get(Integer mOffset, Integer mLimit) {

        return null;
    }

    @Override
    public Integer getTotalCount() {

        return null;
    }
}
