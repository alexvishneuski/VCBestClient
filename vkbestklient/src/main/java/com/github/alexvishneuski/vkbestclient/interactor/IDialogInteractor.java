package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;

import java.util.List;

/**
 * Created by Asus on 29.11.2017.
 */

public interface IDialogInteractor {

    List<MessageInDialogs> getMessagesInDialogListFromRepo(int pCount, int pOffset);

    List<MessageInDialogs> getMessagesInDialogListFromVKApi(int pCount, int pOffset);

    List<MessageInDialogs> getMessagesInDialogListFromDB(int pCount, int pOffset);

    int getDialogsTotalCount();
}


