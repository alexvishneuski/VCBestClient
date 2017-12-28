package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
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
    /*==========================================================================================*/

    /*steps:

                * 1. try get info from DB
                * 2. if false getting info from VK API
                *   2.1 getting array dialoglist - VK API (List)
                *   2.2 extract array contactuser Ids (List)
                *   2.3 getting array user's Info  by ids - VK API (HashMap id - USer)
                *   2.4 mapping userInfo and dialog list into array messageInDialog (List)
                * 3. save info into DB
                 * */


    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromRepo(int pCount, int pOffset) {

        //List<MessageInDialogs> msg;
        //msg = this.getMessagesInDialogListFromDB(pCount, pOffset);
        //if success - return msg
        //if false -
        //msg = this.getMessagesInDialogListFromVKApi(pCount, pOffset);
        //save msg into DB;
        //return or go to DB
        List<MessageInDialogs> msg = this.getMessagesInDialogListFromVKApi(pCount, pOffset);

        return msg;
    }


    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromVKApi(int pCount, int pOffset) {

        List<MessageInDialogs> domainMessages = new ArrayList<>();

        //TODO make sort of currrentUserHolder
        UserInDialogs currentUser = mUserInteractor.getCurrentUserDomain();
        UserInDialogs contactUser;

        List<Integer> contactUserIds = new ArrayList<>();

        // 1. getting dialoglist - VK API
        List<VKApiDialog> dialogs = this.getDialogs(pCount, pOffset);

        for (VKApiDialog dialog : dialogs
                ) {

            VKApiMessage message = dialog.getMessage();

            //todo extract to converter
            MessageInDialogs domainMessage = new MessageInDialogs();

            domainMessage.setCurrentUser(currentUser);

            contactUser = new UserInDialogs();
            contactUser.setUserId(message.getContactUserId());
            domainMessage.setContactUser(contactUser);

            domainMessage.setId(message.getId());
            domainMessage.setMessageDirection((message.getDirection() == 0) ? MessageDirection.INCOMING : MessageDirection.OUTGOING);
            domainMessage.setMessageSendingDate(message.getSendingDate());
            domainMessage.setMessageTitle(message.getTitle());
            domainMessage.setMessageBody(message.getBody());
            domainMessage.setMessageRead((message.getReadStatus() == 0) ? false : true);

            domainMessages.add(domainMessage);

            //2. extract contactuser Ids
            contactUserIds.add(message.getContactUserId());
        }


        //3 getting array user's Info  by ids

        List<UserInDialogs> users = mUserInteractor.getDomainUsers(contactUserIds);

        SparseArray<UserInDialogs> usersInDialogs = new SparseArray<>();

        for (UserInDialogs user : users
                ) {
            usersInDialogs.append(user.getUserId(), user);
        }

        //4 mapping userInfo and dialog list into array messageInDialog (List)

        for (MessageInDialogs message : domainMessages
                ) {
            message.setContactUser(usersInDialogs.get(message.getContactUser().getUserId()));
        }

        //stub
        return domainMessages;
    }

    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromDB(int pCount, int pOffset) {
        throw new UnsupportedOperationException("method in DialogInteractorImpl isn't supported jet");
    }

        /*Result as Repository DB API*/
    /*==========================================================================================*/


    //TODO remove methods are bottom to repository
     /*Result as Repository VK API - sort of RequestBuilders*/
    /*==========================================================================================*/


    private List<VKApiDialog> getDialogs(int pCount, int pOffset) {
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

    //TODO make getDialogsTotalCount() in Interactor
    @Override
    public int getDialogsTotalCount() {

        VKApiGetDialogsParams dialogsParams = VKApiGetDialogsParams.getBuilder().build();
        VKApiUri dialogsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(dialogsParams)
                .build();

        return mDialogVKApiNetworkingImpl.getDialogsTotalCount(dialogsUri);
    }
}
