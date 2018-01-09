package com.github.alexvishneuski.vkbestclient.repository.networking.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.IMessageHistoryNet;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IMessagesHistoryVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.MessagesHistoryVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiMessagesGetHistoryParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;


public class MessageHistoryNetImpl implements IMessageHistoryNet {

    private final String TAG = this.getClass().getSimpleName();

    private IMessagesHistoryVKApiNetworking mMessagesHistoryVKApiNetworkingImpl = new MessagesHistoryVKApiNetworkingImpl();

    @Override
    public List<VKApiMessage> get(Integer pOffset, Integer pLimit) {
        Log.d(TAG, "get() called with: mOffset = [" + pOffset + "], mLimit = [" + pLimit + "]");

        List<VKApiMessage> messagesHistory = new ArrayList<>();

        String msgOffset = String.valueOf(pOffset);
        String msgCount = String.valueOf(pLimit);

        VKApiMessagesGetHistoryParams msgsParams = VKApiMessagesGetHistoryParams.getBuilder().setCount(msgCount).setOffset(msgOffset).build();
        VKApiUri msgssUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(msgsParams)
                .build();

        messagesHistory.addAll(mMessagesHistoryVKApiNetworkingImpl.get(msgssUri));

        Log.d(TAG, "get returns messages in history");

        return messagesHistory;
    }

    @Override
    public Integer getTotalCount() {

        return null;
    }
}
