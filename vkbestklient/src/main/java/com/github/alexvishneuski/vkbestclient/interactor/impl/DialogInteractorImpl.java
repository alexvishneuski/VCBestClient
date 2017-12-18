package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Dialog;
import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.datamodel.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
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

    private IUserInteractor mUserInteractor = new UserInteractorImpl();

    @Override
    public List<VKApiDialog> getDialogs() {
        Log.d(TAG, "getDialogs called ");
        List<VKApiDialog> dialogs = new ArrayList<>();
        dialogs.addAll(mDialogVKApiNetworkingImpl.getDialogs());

        Log.d(TAG, "getDialogs returns dialogs");

        return dialogs;
    }


    /*
    Message Domain (VKAPI/DB-> Interactor)
    ================
   + private int mId;
    +private int mCurrentUserId;
    +private int mContactUserId;
    +private MessageDirection mMessageDirection;
    +private int mMessageSendingDate;
    +private String mMessageTitle;
    +private String mMessageBody;
    +private boolean mIsMessageRead;

    Message DB (Interactor -> DB)
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
        List<Message> domainMessages = new ArrayList<>();
        UserInDialogListViewModel mCurrentUser = null;
        UserInDialogListViewModel mContactUser = null;

        //TODO make sort of currrentUserHolder
        int currentUserId = mUserInteractor.getCurrentUser().getId();


        List<VKApiDialog> dialogs = this.getDialogs();

        for (VKApiDialog dialog : dialogs
                ) {

            VKApiMessage message = dialog.getMessage();

            //todo extract to converter
            Message domainMessage = new Message();

            domainMessage.setId(message.getId());
            domainMessage.setContactUserId(message.getContactUserId());
            domainMessage.setCurrentUserId(currentUserId);
            domainMessage.setMessageDirection((message.getDirection() == 0) ? MessageDirection.INCOMING : MessageDirection.OUTGOING);
            domainMessage.setMessageSendingDate(message.getSendingDate());
            domainMessage.setMessageTitle(message.getTitle());
            domainMessage.setMessageBody(message.getBody());
            domainMessage.setMessageRead((message.getReadStatus() == 0) ? false : true);

            domainMessages.add(domainMessage);
        }

        return domainMessages;
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
