package com.github.alexvishneuski.vkbestclient.repository.database.operations;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.alexvishneuski.vkbestclient.repository.database.operations.impl.DbOperations;
import com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector.SqlConnectorSimple;
import com.github.alexvishneuski.vkbestclient.util.ContextHolder;

public interface IDbOperations {

    int insert(String table, ContentValues values);

    int bulkInsert(String table, ContentValues[] values);

    Cursor query(
            String table, @Nullable final String[] projection, @Nullable final String selection,
            @Nullable final String[] selectionArgs, @Nullable final String sortOrder);

    int delete(String table, @Nullable final String selection, @Nullable final String[] selectionArgs);

    int update(@NonNull final String table, @Nullable final ContentValues values, @Nullable final String selection, @Nullable final String[] selectionArgs);

    final class Imp {

        public static final String SYSTEM_SERVICE_NAME = "IDbOperations";

        public static IDbOperations newInstance() {
            return new DbOperations(new SqlConnectorSimple(ContextHolder.getContext()));
        }

        @SuppressLint("WrongConstant")
        public static IDbOperations getInstance() {
            return (IDbOperations) ContextHolder.getContext().getSystemService(SYSTEM_SERVICE_NAME);
        }

    }
}
