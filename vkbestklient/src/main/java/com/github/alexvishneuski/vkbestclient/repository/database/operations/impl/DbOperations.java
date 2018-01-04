package com.github.alexvishneuski.vkbestclient.repository.database.operations.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.database.operations.IDbOperations;


public class DbOperations implements IDbOperations {

    private final SQLiteOpenHelper mHelper;

    public DbOperations(final SQLiteOpenHelper pHelper) {
        mHelper = pHelper;
    }

    @Override
    public int insert(String table, ContentValues values) {

        final SQLiteDatabase mWritableConnection = mHelper.getWritableDatabase();
        long id = 0;
        mWritableConnection.beginTransaction();

        try {
            id = mWritableConnection.insert(table, "", values);

            mWritableConnection.setTransactionSuccessful();
        } catch (final Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            mWritableConnection.endTransaction();
        }

        return (int) id;
    }

    @Override
    public int bulkInsert(String table, ContentValues[] values) {
        final SQLiteDatabase mWritableConnection = mHelper.getWritableDatabase();
        int insertedCount = 0;

        mWritableConnection.beginTransaction();

        try {
            for (final ContentValues value : values) {
                mWritableConnection.insert(table, "", value);

                insertedCount++;
            }

            mWritableConnection.setTransactionSuccessful();
        } catch (final Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            mWritableConnection.endTransaction();
        }

        return insertedCount;
    }

    @Override
    public Cursor query(String table, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        return mHelper.getReadableDatabase().query(table, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int delete(String table, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase mWritableConnection = mHelper.getWritableDatabase();
        int deletedCount = 0;

        mWritableConnection.beginTransaction();

        try {
            deletedCount = mWritableConnection.delete(table, selection, selectionArgs);

            mWritableConnection.setTransactionSuccessful();
        } catch (final Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            mWritableConnection.endTransaction();
        }

        return deletedCount;
    }

    @Override
    public int update(@NonNull String table, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final SQLiteDatabase mWritableConnection = mHelper.getWritableDatabase();
        int updatedCount = 0;

        mWritableConnection.beginTransaction();

        try {
            updatedCount = mWritableConnection.update(table, values, selection, selectionArgs);

            mWritableConnection.setTransactionSuccessful();
        } catch (final Exception e) {
            Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            mWritableConnection.endTransaction();
        }

        return updatedCount;
    }
}

