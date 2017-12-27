package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.datamodel.MessageDirection;
import com.github.alexvishneuski.vkbestclient.datamodel.User;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class DialogInteractorImpl implements IDialogInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogVKApiNetworking mDialogVKApiNetworkingImpl = new DialogVKApiNetworkingImpl();

    private IUserInteractor mUserInteractor = new UserInteractorImpl();

    /*Result as Repository API*/

    @Override
    public List<VKApiDialog> getDialogs(int pCount, int pOffset) {
        Log.d(TAG, "getDialogs called ");
        List<VKApiDialog> dialogs = new ArrayList<>();

        String dialogCount = String.valueOf(pCount);
        String dialogOffset = String.valueOf(pOffset);

        VKApiGetDialogsParams dialogsParams = VKApiGetDialogsParams.getBuilder().setCount(dialogCount).setOffset(dialogOffset).build();
        VKApiUri dialogsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(dialogsParams)
                .build();

        dialogs.addAll(mDialogVKApiNetworkingImpl.getDialogs(dialogsUri));

        Log.d(TAG, "getDialogs returns dialogs");

        return dialogs;
    }


    /*
    Message Domain  (VKAPI/DB-> Interactor)
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

    /*Result as own Interactor API*/

    @Override
    public List<Message> getMessagesForDialogList(int pCount, int pOffset) {

        /*steps:
        * 1. getting dialoglist - VK API
        * 2. extract contactuser Ids
        * 3. getting users Info by ids - VK API
         * 4. mapping userInfo into dialogllist
        * */

        List<Message> domainMessages = new ArrayList<>();
        User mCurrentUser = null;
        User mContactUser = null;

        //TODO make sort of currrentUserHolder
        int currentUserId = mUserInteractor.getCurrentUser().getId();


        List<VKApiDialog> dialogs = this.getDialogs(pCount, pOffset);

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
    public int getDialogsTotalCount() {

        VKApiGetDialogsParams dialogsParams = VKApiGetDialogsParams.getBuilder().build();
        VKApiUri dialogsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(dialogsParams)
                .build();

        int dialogCount = mDialogVKApiNetworkingImpl.getTotalDialogsCount(dialogsUri);

        return dialogCount;
    }
}
