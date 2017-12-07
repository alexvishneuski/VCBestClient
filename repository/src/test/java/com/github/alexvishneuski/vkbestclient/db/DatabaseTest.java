package com.github.alexvishneuski.vkbestclient.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.alexvishneuski.vkbestclient.Constants;
import com.github.alexvishneuski.vkbestclient.database.SqlConnector;
import com.github.alexvishneuski.vkbestclient.database.model.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repo.BuildConfig;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.UsersTable;
import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = Constants.SDK_VERSION
)
public class DatabaseTest {

    private SqlConnector mSqlConnector;

    @Before
    public void setUp() {
        mSqlConnector = new SqlConnector(RuntimeEnvironment.application);
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
        UserDbModel[] userArray = prepareUsersForInsertIntoDb();
        assertEquals(userArray.length, 3);

        SQLiteDatabase writeConnection = mSqlConnector.getWritableDatabase();
        writeConnection.beginTransaction();

        for (UserDbModel user : userArray) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(UsersTable.FIRST_NAME, user.getFirstName());
            contentValues.put(UsersTable.AVATAR_PATH, user.getAvatarPath());

            //TODO read about nullColumnHack
            //TODO read about conflicts
            writeConnection.insert(UsersTable.TABLE_NAME, null, contentValues);
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

        Cursor usersDbCursor = readableConnection.query(UsersTable.TABLE_NAME, null, null,
                null, null, null, null, null);

        assertEquals(usersDbCursor.getCount(), 3);

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

    private UserDbModel[] prepareUsersForInsertIntoDb() {
        //TODO fill data
        return new UserDbModel[]{new UserDbModel(), new UserDbModel(), new UserDbModel()};
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
