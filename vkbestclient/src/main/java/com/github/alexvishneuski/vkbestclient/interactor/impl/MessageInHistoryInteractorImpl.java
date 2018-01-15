package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.utils.MessageConverter;
import com.github.alexvishneuski.vkbestclient.repository.networking.IDialogsHistoryNet;
import com.github.alexvishneuski.vkbestclient.repository.networking.impl.DialogsHistoryNetImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;

import java.util.ArrayList;
import java.util.List;

public class MessageInHistoryInteractorImpl implements IMessageInHistoryInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogsHistoryNet mDialogsHistoryNet = new DialogsHistoryNetImpl();
    private IUserInteractor mUserInteractor = new UserInteractorImpl();

    @Override
    public int getMessagesInHistoryTotalCount(int pContactUserId) {
        Log.d(TAG, "getMessagesInHistoryTotalCount() called with: pContactUserId = [" + pContactUserId + "]");

        return mDialogsHistoryNet.getMessagesCount(pContactUserId);
    }

    @Override
    public List<MessageInDialogs> getMessagesInHistoryFromRepo(int pOffset, int pLimit, int pContactUserId) {
        Log.d(TAG, "getMessagesInHistoryFromRepo() called with: pOffset = [" + pOffset + "], pLimit = [" + pLimit + "], pContactUserId = [" + pContactUserId + "]");

        List<MessageInDialogs> msgs = getMessagesInHistoryFromVKApi(pOffset, pLimit, pContactUserId);
        Log.d(TAG, "getMessagesInHistoryFromRepo() returned: [" + msgs.size() + "] messages");

        return msgs;
    }

    private List<MessageInDialogs> getMessagesInHistoryFromVKApi(int pOffset, int pLimit, int pContactUserId) {
        Log.d(TAG, "getMessagesInHistoryFromRepo() called with: pOffset = [" + pOffset + "], pLimit = [" + pLimit + "], pContactUserId = [" + pContactUserId + "]");

        List<MessageInDialogs> msgs = new ArrayList<>();
        UserInDialogs currentUser = mUserInteractor.getCurrentUser();

        MessageInDialogs damainMsg;
        final List<VKApiMessage> msgsFromVkApi = mDialogsHistoryNet.getByUserId(pOffset, pLimit, pContactUserId);

        for (VKApiMessage item : msgsFromVkApi
                ) {
            damainMsg = MessageConverter.convertMsgFromVKApiToDomain(item, currentUser);
            msgs.add(damainMsg);
        }

        return msgs;
    }
}
