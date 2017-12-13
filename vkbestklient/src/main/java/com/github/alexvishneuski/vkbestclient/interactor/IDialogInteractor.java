package com.github.alexvishneuski.vkbestclient.interactor;

import com.github.alexvishneuski.vkbestclient.datamodel.Dialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;

import java.util.List;

/**
 * Created by Asus on 29.11.2017.
 */

public interface IDialogInteractor {

    /**
     * @return list of current user's dialogs, default 20 items
     */
    List<VKApiDialog> getDialogList();

    /**
     * @param pCount count of dialog's items in request
     * @return list of current user's dialogs
     */
    List<Dialog> getDialogList(int pCount);


    //TODO delete after testing
    String getResultAsString();
}


