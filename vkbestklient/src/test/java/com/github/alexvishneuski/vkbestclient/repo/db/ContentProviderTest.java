package com.github.alexvishneuski.vkbestclient.repo.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.UserDbModel;
import com.github.alexvishneuski.vkbestclient.repository.database.provider.CustomContentProvider;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimple;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UserDb;
import com.github.alexvishneuski.vkbestclient.repository.database.util.DbUtils;
import com.github.alexvishneuski.vkbestclient.util.ConstantsUtil;
import com.github.alexvishneuski.vkbestclient.util.ContextHolder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(
        constants = BuildConfig.class,
        sdk = ConstantsUtil.AppConstants.SDK_VERSION
)
public class ContentProviderTest {

    public final String TAG = this.getClass().getSimpleName();

    private static final String AUTHORITY = "com.github.alexvishneuski.vkbestclient.repository.database.provider.CustomContentProvider";

    private SQLiteOpenHelper mSqlConnector;

    private SQLiteDatabase mWritableConnection;
    private SQLiteDatabase mReadableConnection;
    private Cursor mCursor;
    private ContentProvider mContentProvider;
    private ContentResolver mContentResolver;

    @Before
    public void setUp() {
        mSqlConnector = new SqlConnectorSimple(RuntimeEnvironment.application);
        mWritableConnection = mSqlConnector.getWritableDatabase();
        mReadableConnection = mSqlConnector.getReadableDatabase();
        mContentProvider = new CustomContentProvider();
        mContentProvider.onCreate();
        ShadowContentResolver.registerProviderInternal(
                AUTHORITY, mContentProvider);


        mContentResolver = RuntimeEnvironment.application.getContentResolver();
//        mContentResolver = ContextHolder.getContext().getContentResolver();
    }

    @After
    public void closeCursor() {
        if (mCursor != null) {
            mCursor.close();
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

        ContentValues values = new ContentValues();
        values.put(UserDb._ID, parId);
        values.put(UserDb.FIRST_NAME, parFirstName);
        values.put(UserDb.LAST_NAME, parLastName);
        values.put(UserDb.AVATAR_PATH, parAvatar);

        Class clazz = UserDb.class;

        //inserting user & getting Uri of inserted user
        Uri uriInserted = mContentResolver.insert(DbUtils.getTableUri(UserDb.class), values);

        assert uriInserted != null;
        //read user
        mCursor = mContentResolver.query(uriInserted, null, null, null, null);

        assert mCursor != null;
        // Assert.assertEquals(1, mCursor.getCount());

        mCursor.moveToFirst();

        int id = mCursor.getInt(mCursor.getColumnIndex(UserDb._ID));
        String firstName = mCursor.getString(mCursor.getColumnIndex(UserDb.FIRST_NAME));
        String lastName = mCursor.getString(mCursor.getColumnIndex(UserDb.LAST_NAME));
        String avatarPath = mCursor.getString(mCursor.getColumnIndex(UserDb.AVATAR_PATH));

        UserDbModel userFromDb = new UserDbModel(id, firstName, lastName, avatarPath);


        //asserting
        assertTrue("item's count for inserting into Db and after getting from Db must the same",
                1 == mCursor.getCount());

        assertEquals("user before inserting and after must be equals", userForInsert, userFromDb);

        assertEquals("user's id before inserting and after must be equals", userForInsert.getId(), userFromDb.getId());
        assertEquals("user's first name before inserting and after must be equals", userForInsert.getFirstName(), userFromDb.getFirstName());
        assertEquals("user's last name before inserting and after must be equals", userForInsert.getLastName(), userFromDb.getLastName());
        assertEquals("user's avatar before inserting and after must be equals", userForInsert.getAvatarPath(), userFromDb.getAvatarPath());
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
