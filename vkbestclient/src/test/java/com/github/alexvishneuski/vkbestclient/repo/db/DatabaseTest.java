package com.github.alexvishneuski.vkbestclient.repo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.sql.Tables;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimple;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UserDb;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = ConstantsUtil.AppConstants.SDK_VERSION
)
public class DatabaseTest {

    private final String TAG = this.getClass().getSimpleName();

    private SQLiteDatabase mWritableConnection;
    private SQLiteDatabase mReadableConnection;
    private Cursor mCursor;

    @Before
    public void setUp() {
        SQLiteOpenHelper sqlConnector = new SqlConnectorSimple(RuntimeEnvironment.application);
        mWritableConnection = sqlConnector.getWritableDatabase();
        mReadableConnection = sqlConnector.getReadableDatabase();
    }

    @After
    public void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Test
    public void insertUserList() {
        Log.d(TAG, "insertUserList called ");

        //inserting 2 users
        List<UserDbModel> usersForInserting = generateUsers(2);

        mWritableConnection.beginTransaction();

        for (int i = 0; i < usersForInserting.size(); i++) {
            UserDbModel user = usersForInserting.get(i);
            mWritableConnection.execSQL(
                    Tables.INSERT_USER,
                    new Object[]{
                            user.getId(), user.getFirstName(), user.getLastName(), user.getAvatarPath()});
        }

        mWritableConnection.setTransactionSuccessful();
        mWritableConnection.endTransaction();

        //getting 2 users
        mReadableConnection.beginTransaction();

        mCursor = mReadableConnection.query(UserDb.TABLE_NAME,
                null, null, null, null, null, null);
        Log.d(TAG, "insertUserList: cursor returned " + mCursor.getCount() + " rows");

        mReadableConnection.setTransactionSuccessful();
        mReadableConnection.endTransaction();

        //asserting
        assertTrue("item's count for inserting into Db and after getting from Db must the same",
                usersForInserting.size() == mCursor.getCount());
    }

    @Test
    public void getUserList() {
        Log.d(TAG, "getUserList called ");

        //inserting 3 users
        List<UserDbModel> usersForInserting = generateUsers(3);

        SparseArray<UserDbModel> usersBefore = new SparseArray<>();

        for (UserDbModel user : usersForInserting
                ) {
            usersBefore.append(user.getId(), user);
        }

        mWritableConnection.beginTransaction();

        for (int i = 0; i < usersForInserting.size(); i++) {
            UserDbModel user = usersForInserting.get(i);
            mWritableConnection.execSQL(
                    Tables.INSERT_USER,
                    new Object[]{
                            user.getId(), user.getFirstName(), user.getLastName(), user.getAvatarPath()});
        }

        mWritableConnection.setTransactionSuccessful();
        mWritableConnection.endTransaction();

        //getting 3 users
        List<UserDbModel> usersFromDb = new ArrayList<>();

        mReadableConnection.beginTransaction();

        mCursor = mReadableConnection.query(UserDb.TABLE_NAME,
                null, null, null, null, null, null);

        if (mCursor.getCount() != 0) {

            mCursor.moveToFirst();
            do {

                int id = mCursor.getInt(mCursor.getColumnIndex(UserDb._ID));
                String firstName = mCursor.getString(mCursor.getColumnIndex(UserDb.FIRST_NAME));
                String lastName = mCursor.getString(mCursor.getColumnIndex(UserDb.LAST_NAME));
                String avatarPath = mCursor.getString(mCursor.getColumnIndex(UserDb.AVATAR_PATH));

                UserDbModel userFromDb = new UserDbModel(id, firstName, avatarPath);
                usersFromDb.add(userFromDb);
            }
            while (mCursor.moveToNext());
            Log.d(TAG, "getUserList: cursor returned " + usersFromDb.size() + " rows");

        } else {
            Log.d(TAG, "getUserList: cursor returned 0 rows");
        }

        SparseArray<UserDbModel> usersAfter = new SparseArray<>();
        for (UserDbModel user : usersFromDb
                ) {
            usersAfter.append(user.getId(), user);
        }

        mReadableConnection.setTransactionSuccessful();
        mReadableConnection.endTransaction();

        //asserting
        assertTrue("item's count for inserting into Db and after getting from Db must be equals",
                usersForInserting.size() == usersFromDb.size());

        for (UserDbModel user : usersFromDb
                ) {
            int id = user.getId();
            assertTrue("item before inserting must be equals to item after getting from Db",
                    usersBefore.get(id).equals(usersAfter.get(id)));
        }
    }

    @Test
    public void insertUser() {
        Log.d(TAG, "insertUser called ");

        //inserting one user
        UserDbModel userForInsert = generateUsers(1).get(0);
        Log.d(TAG, "insertUser: " + userForInsert.toString());

        String parId = String.valueOf(userForInsert.getId());
        String parFirstName = userForInsert.getFirstName();
        String parLastName = userForInsert.getLastName();
        String parAvatar = userForInsert.getAvatarPath();

        mWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(UserDb._ID, parId);
        values.put(UserDb.FIRST_NAME, parFirstName);
        values.put(UserDb.LAST_NAME, parLastName);
        values.put(UserDb.AVATAR_PATH, parAvatar);

        //getting id of inserted user
        int userForInsertId = (int) mWritableConnection.insert(
                UserDb.TABLE_NAME, null, values);

        mWritableConnection.setTransactionSuccessful();
        mWritableConnection.endTransaction();

        //getting inserted user with where
        mReadableConnection.beginTransaction();

        mCursor = mReadableConnection.query(
                UserDb.TABLE_NAME,
                new String[]{
                        UserDb._ID,
                        UserDb.FIRST_NAME,
                        UserDb.LAST_NAME,
                        UserDb.AVATAR_PATH},
                UserDb._ID + "=? AND " +
                        UserDb.FIRST_NAME + "=? AND " +
                        UserDb.LAST_NAME + "=? AND " +
                        UserDb.AVATAR_PATH + "=? ",
                new String[]{parId, parFirstName, parLastName, parAvatar},
                null, null, null
        );

        Log.d(TAG, "insertUser: cursor returned " + mCursor.getCount() + " rows");

        mCursor.moveToFirst();

        int id = mCursor.getInt(mCursor.getColumnIndex(UserDb._ID));
        String firstName = mCursor.getString(mCursor.getColumnIndex(UserDb.FIRST_NAME));
        String lastName = mCursor.getString(mCursor.getColumnIndex(UserDb.LAST_NAME));
        String avatarPath = mCursor.getString(mCursor.getColumnIndex(UserDb.AVATAR_PATH));

        UserDbModel userFromDb = new UserDbModel(id, firstName, avatarPath);

        mReadableConnection.setTransactionSuccessful();
        mReadableConnection.endTransaction();

        //asserting
        assertTrue("item's count for inserting into Db and after getting from Db must the same",
                1 == mCursor.getCount());

        assertEquals("user before inserting and after must be equals", userForInsert, userFromDb);

        assertEquals("user's id before inserting and after must be equals", userForInsert.getId(), userFromDb.getId());
        assertEquals("user's first name before inserting and after must be equals", userForInsert.getFirstName(), userFromDb.getFirstName());
        assertEquals("user's last name before inserting and after must be equals", userForInsert.getLastName(), userFromDb.getLastName());
        assertEquals("user's avatar before inserting and after must be equals", userForInsert.getAvatarPath(), userFromDb.getAvatarPath());
    }


    private List<UserDbModel> generateUsers(int pCount) {
        List<UserDbModel> users = new ArrayList<>();
        UserDbModel user;

        for (int i = 0; i < pCount; i++) {
            user = new UserDbModel(i, "FirstName " + i, "AvatarPath " + i);
            users.add(user);
        }

        return users;
    }
}
