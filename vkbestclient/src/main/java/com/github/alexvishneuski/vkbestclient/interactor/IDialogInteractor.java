package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;

import java.util.List;

public interface IDialogInteractor {

    List<MessageInDialogs> getMessagesInDialogListFromRepo(int pCount, int pOffset);

    List<MessageInDialogs> getMessagesInDialogListFromVKApi(int pCount, int pOffset);

    List<MessageInDialogs> getMessagesInDialogListFromDB(int pCount, int pOffset);

    int insertMessageIntoDB(MessageInDialogs pMessage);

    int bulkInsertMessageIntoDB(List<MessageInDialogs> pMessages);

    int getDialogsTotalCount();
}


