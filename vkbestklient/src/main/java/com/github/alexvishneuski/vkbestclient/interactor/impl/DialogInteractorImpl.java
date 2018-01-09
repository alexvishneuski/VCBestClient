package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.utils.MessageConverter;
import com.github.alexvishneuski.vkbestclient.repository.database.IMessageRepoDb;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.impl.MessageRepoDbImpl;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.IDbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.impl.DbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimple;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UserDb;
import com.github.alexvishneuski.vkbestclient.repository.database.util.DbUtils;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;
import com.github.alexvishneuski.vkbestclient.util.ContextHolder;

import java.util.ArrayList;
import java.util.List;

public class DialogInteractorImpl implements IDialogInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserInteractor mUserInteractor = new UserInteractorImpl();

    private IDialogVKApiNetworking mDialogVKApiNetworkingImpl = new DialogVKApiNetworkingImpl();

    private IMessageRepoDb mIMessageRepoDb = new MessageRepoDbImpl();

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
        Log.d(TAG, "getMessagesInDialogListFromRepo() called with: pCount = [" + pCount + "], pOffset = [" + pOffset + "]");

        //0. compare count items on server and local

        //1. List<MessageInDialogs> msg;
        //2. msg = this.getMessagesInDialogListFromDB(pCount, pOffset);
        //3.    if success - return msg
        //4.    if false -
        //5. msg = this.getMessagesInDialogListFromVKApi(pCount, pOffset);
        //6. save msg into DB;
        //7. return

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
            if (mIMessageRepoDb.ifInDbExist(id)) {
                Log.d(TAG, "getMessagesInDialogListFromRepo(): msg exists already -> updating into DB with id [" + id + "]");
                mIMessageRepoDb.update(msgItem);
            } else {
                Log.d(TAG, "getMessagesInDialogListFromRepo(): msg doesn't exist -> inserting into DB with id [" + id + "]");
                mIMessageRepoDb.insert(msgItem);
            }
        }

        return messagesInteractor;
    }


    @Override
    public List<MessageInDialogs> getMessagesInDialogListFromVKApi(int pCount, int pOffset) {

        List<MessageInDialogs> domainMessages = new ArrayList<>();

        //TODO make sort of currrentUserHolder
        UserInDialogs currentUser = mUserInteractor.getCurrentUserDomain();
        UserInDialogs contactUser;

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

        /*Result as Repository DB API*/
    /*==========================================================================================*/


    //TODO remove methods are bottom to vk api repository
     /*Result as Repository VK API - sort of RequestBuilders*/
    /*==========================================================================================*/


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

    //TODO make getDialogsTotalCount() in Interactor after moving this method to vk api  repository
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


    /*For testing only*/
    private void insertUser() {
        Log.d(TAG, "insertUser called ");

        int COUNT = 10;

        Log.d(TAG, "insertUser called ");

        //inserting one user
        UserDbModel userForInsert = generateUsers(COUNT).get(0);
        Log.d(TAG, "insertUser: " + userForInsert.toString());

        String parId = String.valueOf(userForInsert.getId());
        String parFirstName = userForInsert.getFirstName();
        String parLastName = userForInsert.getLastName();
        String parAvatar = userForInsert.getAvatarPath();

        ContentValues values = new ContentValues();
        values.put(UserDb._ID, parId);
        values.put(UserDb.FIRST_NAME, parFirstName);
        values.put(UserDb.LAST_NAME, parLastName);
        values.put(UserDb.AVATAR_PATH, parAvatar);

        //ContentResolver resolver = ContextHolder.getContext().getContentResolver();
        IDbOperations dbOperations = new DbOperations(new SqlConnectorSimple(ContextHolder.getContext()));

        //Log.d(TAG, "insertUser: " + (resolver!=null));

        Class userClazz = UserDb.class;


        //if exists user with same _id

        Cursor preCursor = dbOperations.query(
                DbUtils.getTableName(userClazz), new String[]{UserDb._ID},
                UserDb._ID + "=?", new String[]{parId}, null, null);

        if (preCursor.getCount() == 0) {
            //make insert
            dbOperations.insert(DbUtils.getTableName(userClazz), values);
            Log.d(TAG, "================inserting===================");
        } else
            //make update
            dbOperations.update(DbUtils.getTableName(userClazz), values, null, null);
        Log.d(TAG, "================updating===================");

        //read user

        Cursor mCursor = dbOperations.query(DbUtils.getTableName(UserDb.class), null, null, null, null, null);

        assert mCursor != null;
        // Assert.assertEquals(1, mCursor.getCount());

        mCursor.moveToFirst();

        int id = mCursor.getInt(mCursor.getColumnIndex(UserDb._ID));
        String firstName = mCursor.getString(mCursor.getColumnIndex(UserDb.FIRST_NAME));
        String lastName = mCursor.getString(mCursor.getColumnIndex(UserDb.LAST_NAME));
        String avatarPath = mCursor.getString(mCursor.getColumnIndex(UserDb.AVATAR_PATH));

        UserDbModel userFromDb = new UserDbModel(id, firstName, lastName, avatarPath);


        //asserting


        mCursor.close();
    }


    private List<UserDbModel> generateUsers(int pI) {
        List<UserDbModel> users = new ArrayList<>();
        UserDbModel user;

        for (int i = 0; i < pI; i++) {
            user = new UserDbModel(i, "FirstName " + i, "LastName " + i, "AvatarPath " + i);
            users.add(user);
        }

        return users;
    }
}
