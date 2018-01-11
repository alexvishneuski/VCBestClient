package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;

import java.util.List;

public interface IMessageInHistoryInteractor {
    int getDialogsTotalCount();

    List<MessageInDialogs> getMessagesInHistoryFromRepo(int pCount, int pOffset);

}
