package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.utils.MessageConverter;
import com.github.alexvishneuski.vkbestclient.repository.database.IMessageRepoDb;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.impl.MessageRepoDbImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

public class DialogInteractorImpl implements IDialogInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserInteractor mUserInteractor = new UserInteractorImpl();

    private IDialogVKApiNetworking mDialogVKApiNetworkingImpl = new DialogVKApiNetworkingImpl();

    private IMessageRepoDb mMessageRepoDb = new MessageRepoDbImpl();

    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromRepo(int pCount, int pOffset) {
        Log.d(TAG, "getMessagesInDialogListFromRepo() called with: pCount = [" + pCount + "], pOffset = [" + pOffset + "]");
        //5. msg = this.getMessagesInDialogListFromVKApi(pCount, pOffset);
        List<MessageInDialogs> messagesInteractor = this.getMessagesInDialogListFromVKApi(pCount, pOffset);

        //6. save msg into DB;
        //6.1 preparing container
        List<MessageDbModel> messagesDb = new ArrayList<>();
        //6.2 convert frpm MessageInDialogs to MessageDbModel
        for (MessageInDialogs msg : messagesInteractor
                ) {
            MessageDbModel msgDb = MessageConverter.convertMsgFromDomainToDb(msg);
            messagesDb.add(msgDb);
        }

        //6.3 saving or updating
        for (MessageDbModel msgItem : messagesDb
                ) {
            int id = msgItem.getId();
            if (mMessageRepoDb.ifInDbExist(id)) {
                Log.d(TAG, "getMessagesInDialogListFromRepo(): msg exists already -> updating into DB with id [" + id + "]");
                mMessageRepoDb.update(msgItem);
            } else {
                Log.d(TAG, "getMessagesInDialogListFromRepo(): msg doesn't exist -> inserting into DB with id [" + id + "]");
                mMessageRepoDb.insert(msgItem);
            }
        }

        return messagesInteractor;
    }


    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromVKApi(int pCount, int pOffset) {

        List<MessageInDialogs> domainMessages = new ArrayList<>();

        //TODO make sort of currrentUserHolder
        UserInDialogs currentUser = mUserInteractor.getCurrentUser();

        List<Integer> contactUserIds = new ArrayList<>();

        // 1. getting dialoglist - VK API
        List<VKApiDialog> dialogs = this.getDialogs(pOffset, pCount);

        for (VKApiDialog dialog : dialogs
                ) {
            VKApiMessage message = dialog.getMessage();

            MessageInDialogs domainMessage = MessageConverter.convertMsgFromVKApiToDomain(message, currentUser);
            domainMessages.add(domainMessage);

            //2. extracting contactuser Ids
            contactUserIds.add(message.getContactUserId());
        }

        //3 getting array user's Info  by ids
        List<UserInDialogs> users = mUserInteractor.getDomainUsersBasicInfo(contactUserIds);

        //3.5 using sparse Array for more efficient inserting users to message
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

        return domainMessages;
    }

    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromDB(int pCount, int pOffset) {
        throw new UnsupportedOperationException("method in DialogInteractorImpl isn't supported jet");
    }

    @Override
    public int insertMessageIntoDB(MessageInDialogs pMessage) {
        throw new UnsupportedOperationException("method in DialogInteractorImpl isn't supported jet");
    }

    @Override
    public int bulkInsertMessageIntoDB(List<MessageInDialogs> pMessages) {
        throw new UnsupportedOperationException("method in DialogInteractorImpl isn't supported jet");
    }

    private List<VKApiDialog> getDialogs(int pOffset, int pCount) {
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
