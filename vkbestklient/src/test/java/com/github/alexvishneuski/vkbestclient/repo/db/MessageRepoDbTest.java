package com.github.alexvishneuski.vkbestclient.repo.db;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private Class mClazz;
    private SQLiteOpenHelper mSqlConnector;
    private IDbOperations mOperations;

    private MessageDbModel msgForInserting;
    private MessageDbModel msgFromDb;

    @Before
    public void setUp() {
        mClazz = MessageDb.class;
        mSqlConnector = new SqlConnectorSimpleTest(RuntimeEnvironment.application);
        mOperations = new DbOperations(mSqlConnector);
        mMessageRepoDb = Mockito.spy(new MessageRepoDbImpl(mOperations, mClazz));
    }

    @After
    public void closeCursor() {
    }

    @Test
    public void insertMessage() {
        Log.d(TAG, "insertMessage() called");

        msgForInserting = generateMessage(1).get(0);
        int forInsertingId = msgForInserting.getId();

        int insertedId = mMessageRepoDb.insert(msgForInserting);
        Assert.assertEquals("message's id before inserting and after must be equals", forInsertingId, insertedId);
    }

    @Test
    public void getMessage() {
        Log.d(TAG, "getMessage() called");

        msgForInserting = generateMessage(1).get(0);
        int forInsertingId = msgForInserting.getId();

        int insertedId = mMessageRepoDb.insert(msgForInserting);
        Assert.assertEquals("message's id before inserting and after must be equals", forInsertingId, insertedId);

        msgFromDb = mMessageRepoDb.get(forInsertingId);
        Assert.assertEquals("messages for inserting and from db must be equals", msgForInserting, msgFromDb);
    }


    @Test
    public void getAllMessages() {
    }

    @Test
    public void updateMessage() {
    }

    @Test
    public void deleteMessage() {
    }

    @Test
    public void ifMessageInDbExist() {
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
