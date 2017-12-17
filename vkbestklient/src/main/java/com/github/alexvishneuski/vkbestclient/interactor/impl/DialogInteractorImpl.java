package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Dialog;
import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;

import java.util.ArrayList;
import java.util.List;

public class DialogInteractorImpl implements IDialogInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogVKApiNetworking mDialogVKApiNetworkingImpl = new DialogVKApiNetworkingImpl();

    @Override
    public List<VKApiDialog> getDialogs() {
        Log.d(TAG, "getDialogs called ");
        List<VKApiDialog> dialogs = new ArrayList<>();
        dialogs.addAll(mDialogVKApiNetworkingImpl.getDialogs());

        Log.d(TAG, "getDialogs returns dialogs");

        return dialogs;
    }


    /*
    Message Domain
    ================
    private int mId;
    private int mCurrentUserId;
    private int mContactUserId;
    private MessageDirection mMessageDirection;
    private int mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

    Message DB
    ================
    private int mId;
    private int mAuthor_id;
    private int mRecipient_id;
    private int mMessageSendingDate;
    private String mMessageTitle;
    private String mMessageBody;
    private boolean mIsMessageRead;

    * */

    @Override
    public List<Message> getMessagesForDialogList() {
        List<Message> messages = new ArrayList<>();
        UserInDialogListViewModel mCurrentUser = null;
        UserInDialogListViewModel mContactUser = null;

        List<VKApiDialog> dialogs = this.getDialogs();
        VKApiMessage message = null;

        for (VKApiDialog dialog : dialogs
                ) {
            message = dialog.getMessage();
            int contactUserId = message.getContactUserId();
            int recipientId = message.;



            //getting author

        }

        return null;
    }

    @Override
    public List<Dialog> getDialogs(int pCount) {

        return null;
    }

    /*TODO delete after testing*/
    @Override
    public String getResultAsString() {
        Log.d(TAG, "getResultAsString called");
        String result = mDialogVKApiNetworkingImpl.getDialogListAsString();
        Log.d(TAG, "getResultAsString returned");

        return result;
    }
}
