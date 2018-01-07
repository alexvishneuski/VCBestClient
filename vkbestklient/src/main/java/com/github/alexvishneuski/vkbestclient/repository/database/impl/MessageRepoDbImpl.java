package com.github.alexvishneuski.vkbestclient.repository.database.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.database.IMessageRepoDb;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
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

    public MessageRepoDbImpl() {
    }

    public MessageRepoDbImpl(IDbOperations pOperations, Class pClazz) {
        mOperations = pOperations;
        mClazz = pClazz;
    }

    @Override
    public Integer insert(MessageDbModel pMsg) {
        Log.d(TAG, "insert() called with: pMsg = [" + pMsg + "]");
        ContentValues values = getContentValues(pMsg);
        int inserted = mOperations.insert(DbUtils.getTableName(mClazz), values);

        //TODO delete after testing
        Cursor cursor = mOperations.query(
                DbUtils.getTableName(mClazz), new String[]{MessageDb._ID},
                null, null, null);
        System.out.println(cursor.getCount());
        cursor.close();
        //==========================

        return inserted;
    }


    @Override
    public Integer bulkIsert(List<MessageDbModel> pMsgs) {
        ContentValues[] valuesArray = new ContentValues[pMsgs.size()];

        for (int i = 0; i < pMsgs.size(); i++) {
            valuesArray[i] = getContentValues(pMsgs.get(i));
        }

        int insertedCount = mOperations.bulkInsert(DbUtils.getTableName(mClazz), valuesArray);
        return insertedCount;
    }

    @Override
    public MessageDbModel get(Integer id) {
        return null;
    }

    @Override
    public List<MessageDbModel> getAll() {
        return null;
    }

    @Override
    public void update(MessageDbModel pMsg) {
        Log.d(TAG, "update() called with: pMsg = [" + pMsg + "]");

        //getting msg fields

        String authId = String.valueOf(pMsg.getAuthor_id());
        String recipId = String.valueOf(pMsg.getRecipient_id());
        String title = String.valueOf(pMsg.getMessageTitle());
        String body = String.valueOf(pMsg.getMessageBody());
        String date = String.valueOf(pMsg.getMessageSendingDate());
        String isRead = String.valueOf(pMsg.isMessageRead());

        //build ContentValues
        ContentValues values = new ContentValues();

        values.put(MessageDb.AUTHOR_ID, authId);
        values.put(MessageDb.RECIPIENT_ID, recipId);
        values.put(MessageDb.TITLE, title);
        values.put(MessageDb.BODY, body);
        values.put(MessageDb.CREATED, date);
        values.put(MessageDb.IS_READ, isRead);

        //TODO delete after testing
        Cursor cursor = mOperations.query(
                DbUtils.getTableName(mClazz), new String[]{MessageDb._ID},
                null, null, null);
        System.out.println(cursor.getCount());
        cursor.close();
        //==========================

        mOperations.update(DbUtils.getTableName(mClazz), values, null, null);
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public boolean ifInDbExist(Integer pId) {
        Log.d(TAG, "ifInDbExist() called with: pId = [" + pId + "]");

        String id = String.valueOf(pId);
        Cursor cursor = mOperations.query(
                DbUtils.getTableName(mClazz), new String[]{MessageDb._ID},
                MessageDb._ID + "=?", new String[]{id}, null);
        boolean ifExist = cursor.getCount() != 0;
        cursor.close();

        Log.d(TAG, "ifInDbExist: " + ifExist);
        return ifExist;
    }

    @NonNull
    private ContentValues getContentValues(MessageDbModel pMsg) {
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
        values.put(MessageDb.AUTHOR_ID, authId);
        values.put(MessageDb.RECIPIENT_ID, recipId);
        values.put(MessageDb.TITLE, title);
        values.put(MessageDb.BODY, body);
        values.put(MessageDb.CREATED, date);
        values.put(MessageDb.IS_READ, isRead);

        return values;
    }
}
