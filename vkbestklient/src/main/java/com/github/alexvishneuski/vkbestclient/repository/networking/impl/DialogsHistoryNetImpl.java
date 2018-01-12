package com.github.alexvishneuski.vkbestclient.repository.networking.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.IDialogsHistoryNet;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogsHistoryVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogsHistoryVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiMessagesGetHistoryParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;


public class DialogsHistoryNetImpl implements IDialogsHistoryNet {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogsHistoryVKApiNetworking mMessagesHistoryVKApiNetworkingImpl = new DialogsHistoryVKApiNetworkingImpl();

    @Override
    public List<VKApiMessage> get(Integer pOffset, Integer pLimit) {
        throw new UnsupportedOperationException("this method does not make sense: always is needed the dialog with one interlocutor");
    }

    @Override
    public Integer getTotalCount() {
        throw new UnsupportedOperationException("this method does not make sense: always is needed the messages count by one dialog");
    }

    @Override
    public List<VKApiMessage> getByUserId(Integer pOffset, Integer pLimit, Integer pUserId) {
        Log.d(TAG, "get() called with: pOffset = [" + pOffset + "], pLimit = [" + pLimit + "], pUserId = [" + pUserId + "]");

        List<VKApiMessage> messagesHistory = new ArrayList<>();

        String msgOffset = String.valueOf(pOffset);
        String msgCount = String.valueOf(pLimit);
        String userId = String.valueOf(pUserId);

        VKApiMessagesGetHistoryParams msgsParams =
                VKApiMessagesGetHistoryParams.getBuilder().setCount(msgCount).setOffset(msgOffset).setUserId(userId).build();
        VKApiUri msgsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetHistory.METHOD_NAME)
                .setParameters(msgsParams)
                .build();

        messagesHistory.addAll(mMessagesHistoryVKApiNetworkingImpl.get(msgsUri));

        Log.d(TAG, "get returns ["+messagesHistory.size()+"] messages in dialogs history");

        return messagesHistory;
    }

    @Override
    public Integer getMessagesCount(Integer pContactUserId) {
        Log.d(TAG, "getMessagesCount() called with: pContactUserId = [" + pContactUserId + "]");

        String limitAsString = "1";
        String limit = String.valueOf(limitAsString);
        String userId = String.valueOf(pContactUserId);

        VKApiMessagesGetHistoryParams msgsParams =
                VKApiMessagesGetHistoryParams.getBuilder().setUserId(userId).setCount(limit).build();
        VKApiUri msgsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetHistory.METHOD_NAME)
                .setParameters(msgsParams)
                .build();

        int count = mMessagesHistoryVKApiNetworkingImpl.getTotalCount(msgsUri);

        Log.d(TAG, "getMessagesCount: returned [" + count + "] messages " +
                "in dialog history with user with id [" + pContactUserId + "]");
        return count;
    }
}
