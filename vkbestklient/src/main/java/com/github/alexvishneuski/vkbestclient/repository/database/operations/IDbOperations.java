package com.github.alexvishneuski.vkbestclient.repository.database.operations;

import android.annotation.SuppressLint;
import android.database.Cursor;

import com.github.alexvishneuski.vkbestclient.repository.database.operations.impl.DbOperations;
import com.github.alexvishneuski.vkbestclient.util.ContextHolder;

public interface IDbOperations {

    int insert();

    int bulkInsert();

    Cursor query();

    int delete();

    final class Imp {

        public static final String SYSTEM_SERVICE_NAME = "IDbOperations";

        public static DbOperations newInstance() {
            return new DbOperations();
        }

        @SuppressLint("WrongConstant")
        public static DbOperations getInstance() {
            return (DbOperations) ContextHolder.getContext().getSystemService(SYSTEM_SERVICE_NAME);
        }

    }
}
