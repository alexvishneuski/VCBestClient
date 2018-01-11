package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.networking.IDialogsHistoryNet;
import com.github.alexvishneuski.vkbestclient.repository.networking.impl.DialogsHistoryNetImpl;

import java.util.ArrayList;
import java.util.List;

public class MessageInHistoryInteractorImpl implements IMessageInHistoryInteractor {

    private final String TAG = this.getClass().getSimpleName();

    IDialogsHistoryNet mIDialogsHistoryNet = new DialogsHistoryNetImpl();

    @Override
    public int getMessagesInHistoryTotalCount(int pContactUserId) {
        Log.d(TAG, "getMessagesInHistoryTotalCount() called with: pContactUserId = [" + pContactUserId + "]");

        return mIDialogsHistoryNet.getMessagesCount(pContactUserId);
    }

    @Override
    public List<MessageInDialogs> getMessagesInHistoryFromRepo(int pCount, int pOffset) {

        mIDialogsHistoryNet.get(pOffset, pCount);

        return new ArrayList<>();
    }
}
