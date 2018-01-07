package com.github.alexvishneuski.vkbestclient.repo.db;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.repository.database.IMessageRepoDb;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.impl.MessageRepoDbImpl;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.IDbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.impl.DbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimpleTest;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessageDb;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = ConstantsUtil.AppConstants.SDK_VERSION
)
public class MessageRepoDbTest {

    private final String TAG = this.getClass().getSimpleName();

    private IMessageRepoDb mMessageRepoDb;

    private MessageDbModel mMsgForInserting;
    private List<MessageDbModel> mMsgsForInserting;
    private MessageDbModel mMsgFromDb;
    private List<MessageDbModel> mMsgsFromDb;

    @Before
    public void setUp() {
        Class clazz = MessageDb.class;
        SQLiteOpenHelper sqlConnector = new SqlConnectorSimpleTest(RuntimeEnvironment.application);
        IDbOperations operations = new DbOperations(sqlConnector);
        mMessageRepoDb = Mockito.spy(new MessageRepoDbImpl(operations, clazz));
    }

    @After
    public void closeCursor() {
    }

    @Test
    public void insertMessageTest() {
        Log.d(TAG, "insertMessageTest() called");

        mMsgForInserting = generateMessage(1).get(0);
        int forInsertingId = mMsgForInserting.getId();

        int insertedId = mMessageRepoDb.insert(mMsgForInserting);
        Assert.assertEquals("message's id before inserting and after must be equals", forInsertingId, insertedId);
    }

    @Test
    public void bulkIsertTest() {
        Log.d(TAG, "bulkIsertTest() called");

        int msgForInsertingCount = 5;

        mMsgsForInserting = generateMessage(msgForInsertingCount);
        int insertedCount = mMessageRepoDb.bulkIsert(mMsgsForInserting);
        Assert.assertEquals("message's count before inserting and after must be equals", msgForInsertingCount, insertedCount);

        mMsgsFromDb = mMessageRepoDb.getAll();

        SparseArray<MessageDbModel> msgsArray = new SparseArray<>();

        for (MessageDbModel msg : mMsgsFromDb
                ) {
            msgsArray.append(msg.getId(), msg);
        }

        for (MessageDbModel msg : mMsgsForInserting
                ) {
            Assert.assertEquals("messages for inserting and from db must be equals", msg, msgsArray.get(msg.getId()));
        }

    }

    @Test
    public void getMessageTest() {
        Log.d(TAG, "getMessageTest() called");

        mMsgForInserting = generateMessage(1).get(0);
        int forInsertingId = mMsgForInserting.getId();

        int insertedId = mMessageRepoDb.insert(mMsgForInserting);
        Assert.assertEquals("message's id before inserting and after must be equals", forInsertingId, insertedId);

        mMsgFromDb = mMessageRepoDb.get(forInsertingId);
        Assert.assertEquals("messages for inserting and from db must be equals", mMsgForInserting, mMsgFromDb);
    }


    @Test
    public void getAllMessagesTest() {
        Log.d(TAG, "getAllMessagesTest() called");

        int msgForInsertingCount = 5;

        mMsgsForInserting = generateMessage(msgForInsertingCount);
        int insertedCount = mMessageRepoDb.bulkIsert(mMsgsForInserting);
        Assert.assertEquals("message's count before inserting and after must be equals", msgForInsertingCount, insertedCount);

        mMsgsFromDb = mMessageRepoDb.getAll();

        SparseArray<MessageDbModel> msgsArray = new SparseArray<>();

        for (MessageDbModel msg : mMsgsFromDb
                ) {
            msgsArray.append(msg.getId(), msg);
        }

        for (MessageDbModel msg : mMsgsForInserting
                ) {
            Assert.assertEquals("messages for inserting and from db must be equals", msg, msgsArray.get(msg.getId()));
        }
    }

    @Test
    public void getLimitedMessagesCountWithOffsetTest() {
        Log.d(TAG, "getAllMessagesTest() called");

        int msgForInsertingCount = 5;
        int offset = 2;
        int limit = 1;

        mMsgsForInserting = generateMessage(msgForInsertingCount);
        int insertedCount = mMessageRepoDb.bulkIsert(mMsgsForInserting);
        Assert.assertEquals("message's count before inserting and after must be equals", msgForInsertingCount, insertedCount);

        mMsgsFromDb = mMessageRepoDb.getLimitedPartWithOffset(offset, limit);

        SparseArray<MessageDbModel> msgsArray = new SparseArray<>();

        for (MessageDbModel msg : mMsgsForInserting
                ) {
            msgsArray.append(msg.getId(), msg);
        }

        Assert.assertEquals("message's count from query must be equals limit", limit, mMsgsFromDb.size());
        Assert.assertEquals("messages from query they must meet the offset's requirements", offset, mMsgsFromDb.get(0).getId());

        for (MessageDbModel msg : mMsgsFromDb
                ) {
            Assert.assertEquals("messages for inserting and from db must be equals", msg, msgsArray.get(msg.getId()));
        }
    }

    @Test
    public void updateMessageTest() {
        Log.d(TAG, "updateMessageTest() called");
        //creating msg
        mMsgForInserting = generateMessage(1).get(0);
        int forInsertingId = mMsgForInserting.getId();
        //inserting msg
        int insertedId = mMessageRepoDb.insert(mMsgForInserting);
        //getting inserted msg
        mMsgFromDb = mMessageRepoDb.get(forInsertingId);
        //creating new msg equals old
        MessageDbModel msgForInsertingNew = mMsgForInserting;
        //changing new msg
        msgForInsertingNew.setMessageBody(msgForInsertingNew.getMessageBody() + " updated");
        //updating old message (new instead old )
        mMessageRepoDb.update(msgForInsertingNew);
        int insertedIdNew = mMessageRepoDb.get(msgForInsertingNew.getId()).getId();
        Assert.assertEquals("message's id before updating and after must be equals", insertedId, insertedIdNew);
        MessageDbModel msgFromDbNew = mMessageRepoDb.get(forInsertingId);
        Assert.assertNotEquals("messages for inserting and from db must be equals", mMsgFromDb, msgFromDbNew);
    }

    @Test
    public void deleteMessageTest() {
        Log.d(TAG, "deleteMessageTest() called");

        mMsgForInserting = generateMessage(1).get(0);
        int insertedId = mMessageRepoDb.insert(mMsgForInserting);
        mMessageRepoDb.delete(insertedId);
        boolean isExist = mMessageRepoDb.ifInDbExist(insertedId);
        Assert.assertTrue("message must not exist", !isExist);
    }

    @Test
    public void ifMessageInDbExistTest() {
        Log.d(TAG, "ifMessageInDbExistTest() called");

        mMsgForInserting = generateMessage(1).get(0);
        int insertedId = mMessageRepoDb.insert(mMsgForInserting);
        boolean isExist = mMessageRepoDb.ifInDbExist(insertedId);
        Assert.assertTrue("message must exist", isExist);
    }

    private List<MessageDbModel> generateMessage(int pCount) {
        List<MessageDbModel> msgs = new ArrayList<>();
        MessageDbModel msg;
        int authorId = 1;
        int recipientId = 2;
        int sendingDate = 1477299296;

        for (int i = 0; i < pCount; i++) {
            msg = new MessageDbModel(i, authorId, recipientId, sendingDate, "MessageTitle " + i, "MessageBody " + i, 0);
            msgs.add(msg);
        }

        return msgs;
    }
}
