package com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.github.alexvishneuski.vkbestclient.repository.database.sql.Tables.CREATE_MESSAGES_TABLE;
import static com.github.alexvishneuski.vkbestclient.repository.database.sql.Tables.CREATE_USERS_TABLE;

public class SqlConnectorSimpleTest extends SQLiteOpenHelper {

    private final String TAG = this.getClass().getSimpleName();

    private static final String DATABASE_NAME = "testvkclient.db";

    private static final int DATABASE_VERSION = 1;

    public SqlConnectorSimpleTest(Context context) {
        //TODO read about SQLiteDatabase.CursorFactory
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate called");

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_MESSAGES_TABLE);

        Log.d(TAG, "onCreate: " + CREATE_USERS_TABLE);
        Log.d(TAG, "onCreate: " + CREATE_MESSAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade from " + oldVersion + " to " + newVersion);
    }
}

