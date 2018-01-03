package com.github.alexvishneuski.vkbestclient.repo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.repository.database.SqlConnector;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.sql.Tables;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UsersTableModel;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

    public final String TAG = this.getClass().getSimpleName();

    private SqlConnector mSqlConnector;

    private SQLiteDatabase mWritableConnection;
    private SQLiteDatabase mReadableConnection;
    private Cursor mCursor;

    @Before
    public void setUp() {
        mSqlConnector = new SqlConnector(RuntimeEnvironment.application);
        mWritableConnection = mSqlConnector.getWritableDatabase();
        mReadableConnection = mSqlConnector.getReadableDatabase();
    }

    @After
    public void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Test
    public void insertUsers() {
        Log.d(TAG, "insertUserPreparedSql: called");

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

        mCursor = mReadableConnection.query(UsersTableModel.TABLE_NAME,
                null, null, null, null, null, null);
        Log.d(TAG, "getUserPreparedSql: cursor returned " + mCursor.getCount() + " rows");

        mReadableConnection.setTransactionSuccessful();
        mReadableConnection.endTransaction();

        //asserting
        assertTrue("item's count for inserting into Db and after getting from Db must the same",
                usersForInserting.size() == mCursor.getCount());
    }

//TODO use COntentValues by inserting
    @Test
    public void getUsers() {
        Log.d(TAG, "getUserPreparedSql: called");

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

        mCursor = mReadableConnection.query(UsersTableModel.TABLE_NAME,
                null, null, null, null, null, null);

        if (mCursor.getCount() != 0) {

            mCursor.moveToFirst();
            do {

                int id = mCursor.getInt(mCursor.getColumnIndex(UsersTableModel.ID));
                String firstName = mCursor.getString(mCursor.getColumnIndex(UsersTableModel.FIRST_NAME));
                String lastName = mCursor.getString(mCursor.getColumnIndex(UsersTableModel.LAST_NAME));
                String avatarPath = mCursor.getString(mCursor.getColumnIndex(UsersTableModel.AVATAR_PATH));

                UserDbModel userFromDb = new UserDbModel(id, firstName, lastName, avatarPath);
                usersFromDb.add(userFromDb);
            }
            while (mCursor.moveToNext());
            Log.d(TAG, "getUserPreparedSql: cursor returned " + usersFromDb.size() + " rows");

        } else {
            Log.d(TAG, "getUserPreparedSql: cursor returned 0 rows");
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
    public void updateUserPreparedSql() {

    }

    @Test
    public void deleteUserPreparedSql() {

    }


    @Ignore
    @Test
    public void putUserOverSql() {
/*
        SQLiteDatabase readableConnection = mSqlConnector.getReadableDatabase();
        readableConnection.beginTransaction();
        readableConnection.execSQL(Tables.INSERT_TEST_USER,
                new Object[]{"alex", 124425345232l});
        readableConnection.setTransactionSuccessful();
        readableConnection.endTransaction();
*/
    }

    @Test
    public void putRegisteredUsersToDb() {
        Log.d(TAG, "putRegisteredUsersToDb called");

        UserDbModel[] userArray = prepareUsersForInsertIntoDb();
        assertEquals(3, userArray.length);

        SQLiteDatabase writeConnection = mSqlConnector.getWritableDatabase();
        writeConnection.beginTransaction();

        for (UserDbModel user : userArray) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(UsersTableModel.FIRST_NAME, user.getFirstName());
            contentValues.put(UsersTableModel.LAST_NAME, user.getLastName());
            contentValues.put(UsersTableModel.AVATAR_PATH, user.getAvatarPath());

            //TODO read about nullColumnHack
            //TODO read about conflicts
            long id = writeConnection.insert(UsersTableModel.TABLE_NAME, null, contentValues);
            Log.d(TAG, String.format("added user into Db %s with  id = %d, first name =%s, last name =%s, avatar path = %s: ", UsersTableModel.TABLE_NAME, id, user.getFirstName(), user.getLastName(), user.getAvatarPath()));
            // writeConnection.getPageSize();

        }

        writeConnection.setTransactionSuccessful();
        writeConnection.endTransaction();

        SQLiteDatabase readableConnection = mSqlConnector.getReadableDatabase();
       /* Cursor cursor = readableConnection.query(UserDbModel.TABLE,
                new String[]{UserDbModel.ID, UserDbModel.NAME, UserDbModel.REGISTERED},
                UserDbModel.NAME + "=? AND " + UserDbModel.REGISTERED + " NOT NULL",
                new String[]{"Shelia Chang"}, null, null, null, null);

        final List<RegisteredUser> registeredUsers = new ArrayList<>();
        while (cursor.moveToNext()) {
            String userId = cursor.getString(cursor.getColumnIndex(UserDbModel.ID));
            String userName = cursor.getString(cursor.getColumnIndex(UserDbModel.NAME));
            Long registered = cursor.getLong(cursor.getColumnIndex(UserDbModel.REGISTERED));
            RegisteredUser registeredUser = new RegisteredUser(userName, userId, registered, null);
            registeredUsers.add(registeredUser);
        }

        ///!!! close cursor
        cursor.close();

        assertEquals(registeredUsers.size(), 1);
        assertEquals(registeredUsers.get(0).getUserName(), "Shelia Chang");*/

        Cursor usersDbCursor = readableConnection.query(UsersTableModel.TABLE_NAME, null, null,
                null, null, null, null, null);

        assertEquals(3, usersDbCursor.getCount());

        usersDbCursor.close();

    }

   /* @Test
    public void getUsersFromDb() {
        SQLiteDatabase readableConnection = mSqlConnector.getReadableDatabase();
        Cursor usersDbCursor = readableConnection.query(UserDbModel.TABLE, null,
                null,
                null, null, null, null, null);

        assertEquals(usersDbCursor.getCount(), 6);

        usersDbCursor.close();
    }
*/


    private List<UserDbModel> generateUsers(int pI) {
        List<UserDbModel> users = new ArrayList<>();
        UserDbModel user;

        for (int i = 0; i < pI; i++) {
            user = new UserDbModel(i, "FirstName " + i, "LastName " + i, "AvatarPath " + i);
            users.add(user);
        }

        return users;
    }

    private UserDbModel[] prepareUsersForInsertIntoDb() {
        //TODO fill data
        return new UserDbModel[]{new UserDbModel("1", "2", "3"), new UserDbModel("1", "2", "3"), new UserDbModel("1", "2", "3")};
    }

   /* private RegisteredUser[] readRegisteredUsers() {
        InputStream mockedInputStream = Mocks.stream("generated.json");
        Reader reader = new InputStreamReader(mockedInputStream);

        return new Gson().fromJson(reader, RegisteredUser[].class);
    }*/

   /* private ListRegisteredUsers readRegisteredUsersList() {
        InputStream mockedInputStream = Mocks.stream("generated.json");
        Reader reader = new InputStreamReader(mockedInputStream);

        return new Gson().fromJson(reader, ListRegisteredUsers.class);
    }*/
}
