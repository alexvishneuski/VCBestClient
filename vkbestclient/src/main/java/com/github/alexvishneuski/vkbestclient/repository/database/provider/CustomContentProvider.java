package com.github.alexvishneuski.vkbestclient.repository.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.alexvishneuski.vkbestclient.VKApp;
import com.github.alexvishneuski.vkbestclient.repository.database.operations.IDbOperations;


public class CustomContentProvider extends ContentProvider {

    public static final String PROVIDER_URI = "content://com.github.alexvishneuski.vkbestclient.repository.database.provider.CustomContentProvider";

    private IDbOperations mDatabase;

    @Override
    public boolean onCreate() {
        mDatabase = new VKApp().getDbOperations();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return mDatabase.query(uri.getLastPathSegment(), projection, selection, selectionArgs, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return Uri.parse(uri + "/" + mDatabase.insert(uri.getLastPathSegment(), values));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        return mDatabase.delete(uri.getLastPathSegment(), selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mDatabase.update(uri.getLastPathSegment(), values, selection, selectionArgs);
    }

    @Override
    public int bulkInsert(@NonNull final Uri uri, @NonNull final ContentValues[] values) {

        return mDatabase.bulkInsert(uri.getLastPathSegment(), values);
    }
}