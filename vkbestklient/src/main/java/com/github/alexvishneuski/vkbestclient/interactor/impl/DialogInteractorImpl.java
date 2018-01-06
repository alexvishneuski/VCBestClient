package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
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

        //TODO extract to converter
        //6.1 convert frpm MessageInDialogs to MessageDbModel
        List<MessageDbModel> messagesDb = new ArrayList<>();
        MessageDbModel messageDb = new MessageDbModel();

        for (MessageInDialogs msg : messagesInteractor
                ) {
            messageDb.setId(msg.getId());
            messageDb.setAuthor_id(msg.getMessageDirection().equals(MessageDirection.OUTGOING) ? msg.getCurrentUser().getUserId() : msg.getContactUser().getUserId());
            messageDb.setRecipient_id(msg.getMessageDirection().equals(MessageDirection.INCOMING) ? msg.getCurrentUser().getUserId() : msg.getContactUser().getUserId());
            messageDb.setMessageTitle(msg.getMessageTitle());
            messageDb.setMessageBody(msg.getMessageBody());
            messageDb.setMessageSendingDate(msg.getMessageSendingDate());
            messageDb.setMessageRead(msg.isMessageRead() ? 1 : 0);

            messagesDb.add(messageDb);
        }

        //================convert frpm MessageInDialogs to MessageDbModel


        for (MessageDbModel msgItem : messagesDb
                ) {
            if (mIMessageRepoDb.ifInDbExist(msgItem.getId())) {
                mIMessageRepoDb.update(msgItem);
            } else
                mIMessageRepoDb.insert(msgItem);
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

        List<UserInDialogs> users = mUserInteractor.getDomainUsersBasicInfo(contactUserIds);

        //3.5 use sparse Array for more efficient inserting users to message
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
                UserDb._ID + "=?", new String[]{parId}, null);

        if (preCursor.getCount() == 0) {
            //make insert
            dbOperations.insert(DbUtils.getTableName(userClazz), values);
            Log.d(TAG, "================inserting===================");
        } else
            //make update
            dbOperations.update(DbUtils.getTableName(userClazz), values, null, null);
        Log.d(TAG, "================updating===================");

        //read user

        Cursor mCursor = dbOperations.query(DbUtils.getTableName(UserDb.class), null, null, null, null);

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
