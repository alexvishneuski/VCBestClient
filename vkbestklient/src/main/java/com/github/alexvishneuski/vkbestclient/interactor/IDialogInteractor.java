package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;

import java.util.List;

/**
 * Created by Asus on 29.11.2017.
 */

public interface IDialogInteractor {

    /**
     * @return list of current user's dialogs, default 20 items
     */
    List<VKApiDialog> getDialogs(int pCount, int pOffset);

    /**
     * @return List<Message>
     */
    List<Message> getMessagesForDialogList(int pCount, int pOffset);

    int getDialogsTotalCount();

}


