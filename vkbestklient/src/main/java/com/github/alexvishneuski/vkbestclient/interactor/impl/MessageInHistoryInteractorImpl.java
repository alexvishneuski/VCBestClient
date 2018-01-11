package com.github.alexvishneuski.vkbestclient.interactor.impl;

import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;

import java.util.List;

public class MessageInHistoryInteractorImpl implements IMessageInHistoryInteractor {
    @Override
    public int getDialogsTotalCount() {
        return 0;
    }

    @Override
    public List<MessageInDialogs> getMessagesInHistoryFromRepo(int pCount, int pOffset) {
        return null;
    }
}
