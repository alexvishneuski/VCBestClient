package com.github.alexvishneuski.vkbestclient.repository.database.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.database.IMessageRepoDb;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.IDbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.impl.DbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimple;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessageDb;
import com.github.alexvishneuski.vkbestclient.repository.database.util.DbUtils;
import com.github.alexvishneuski.vkbestclient.util.ContextHolder;

import java.util.List;

public class MessageRepoDbImpl implements IMessageRepoDb {

    private final String TAG = this.getClass().getSimpleName();

    private IDbOperations mOperations = new DbOperations(new SqlConnectorSimple(ContextHolder.getContext()));

    private Class mClazz = MessageDb.class;

    @Override
    public Integer insert(MessageDbModel pMsg) {

        //getting msg fields
        String id = String.valueOf(pMsg.getId());
        String authId = String.valueOf(pMsg.getAuthor_id());
        String recipId = String.valueOf(pMsg.getRecipient_id());
        String title = String.valueOf(pMsg.getMessageTitle());
        String body = String.valueOf(pMsg.getMessageBody());
        String date = String.valueOf(pMsg.getMessageSendingDate());
        String isRead = String.valueOf(pMsg.isMessageRead());

        //build ContentValues
        ContentValues values = new ContentValues();
        values.put(MessageDb._ID, id);
        values.put(MessageDb._ID, authId);
        values.put(MessageDb._ID, recipId);
        values.put(MessageDb._ID, title);
        values.put(MessageDb._ID, body);
        values.put(MessageDb._ID, date);
        values.put(MessageDb._ID, isRead);

        //control if exists user with same _id
        Cursor preCursor = mOperations.query(
                DbUtils.getTableName(mClazz), new String[]{MessageDb._ID},
                MessageDb._ID + "=?", new String[]{id}, null);

        if (preCursor.getCount() == 0) {
            //make insert
            mOperations.insert(DbUtils.getTableName(mClazz), values);
            Log.d(TAG, "inserted message with id = [" + id + "]");
        } else
            //make update
            mOperations.update(DbUtils.getTableName(mClazz), values, null, null);
        Log.d(TAG, "updated message with id = [" + id + "]");

        //read user

        Cursor mCursor = mOperations.query(DbUtils.getTableName(MessageDb.class), null, null, null, null);

        assert mCursor != null;
        // Assert.assertEquals(1, mCursor.getCount());

        mCursor.moveToFirst();

        int id = mCursor.getInt(mCursor.getColumnIndex(MessageDb._ID));
        String firstName = mCursor.getString(mCursor.getColumnIndex(MessageDb.FIRST_NAME));
        String lastName = mCursor.getString(mCursor.getColumnIndex(MessageDb.LAST_NAME));
        String avatarPath = mCursor.getString(mCursor.getColumnIndex(MessageDb.AVATAR_PATH));

        UserDbModel userFromDb = new UserDbModel(id, firstName, lastName, avatarPath);


        //asserting


        mCursor.close();

        return null;
    }

    @Override
    public List<MessageDbModel> getAll() {
        return null;
    }

    @Override
    public void update(MessageDbModel entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public MessageDbModel get(Integer id) {
        return null;
    }

    @Override
    public boolean ifInDbExist(Integer pId) {
        String id = String.valueOf(pId);
        Cursor cursor = mOperations.query(
                DbUtils.getTableName(mClazz), new String[]{MessageDb._ID},
                MessageDb._ID + "=?", new String[]{id}, null);

        return cursor.getCount() != 0;
    }
}
