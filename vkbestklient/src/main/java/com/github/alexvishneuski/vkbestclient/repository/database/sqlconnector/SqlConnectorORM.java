package com.github.alexvishneuski.vkbestclient.repository.database.sqlconnector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbInteger;
import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbLong;
import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbString;
import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbTable;
import com.github.alexvishneuski.vkbestclient.repository.database.orm.models.DbModels;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class SqlConnectorORM extends SQLiteOpenHelper {

    private final String TAG = this.getClass().getSimpleName();

    private static final String DATABASE_NAME = "vkclient.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TEMPLATE =
            "CREATE TABLE IF NOT EXISTS %s (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s)";

    public SqlConnectorORM(Context context) {
        //TODO read about SQLiteDatabase.CursorFactory
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate called");

        createTables(db, DbModels.DB_MODELS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade from " + oldVersion + " to " + newVersion);
    }

    private void createTables(SQLiteDatabase readableConnection, Class<?>[] tableClassArray) {
        readableConnection.beginTransaction();

        try {
            for (Class<?> tableClass : tableClassArray) {
                dbTable dbTableAnnotation = tableClass.getAnnotation(dbTable.class);
                if (dbTableAnnotation != null) {
                    String dbTableName = dbTableAnnotation.value();

                    if (TextUtils.isEmpty(dbTableName)) {
                        return;
                    }

                    StringBuilder stringBuilder = new StringBuilder();

                    Field[] fields = tableClass.getFields();
                    final int fieldCount = fields.length;
                    for (int i = 0; i < fieldCount; i++) {
                        Field field = fields[i];
                        Annotation[] fieldAnnotations = field.getAnnotations();
                        String fieldName = (String) field.get(null);
                        String fieldType = null;

                        for (Annotation fieldAnnotation : fieldAnnotations) {
                            Class<?> fieldAnnotationType = fieldAnnotation.annotationType();
                            if (fieldAnnotationType.equals(dbString.class)) {
                                fieldType = ((dbString) fieldAnnotation).value();
                            } else if (fieldAnnotationType.equals(dbLong.class)) {
                                fieldType = ((dbLong) fieldAnnotation).value();
                            } else if (fieldAnnotationType.equals(dbInteger.class)) {
                                fieldType = ((dbInteger) fieldAnnotation).value();
                            }
                            if (!TextUtils.isEmpty(fieldType)) {
                                stringBuilder.append(fieldName + " " + fieldType + ",");
                            }
                        }
                    }

                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                    String tableCreteQuery = String.format(TABLE_TEMPLATE, dbTableName, stringBuilder.toString());
                    readableConnection.execSQL(tableCreteQuery);
                }
            }
            readableConnection.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "create table exception", e);
        } finally {
            readableConnection.endTransaction();
        }
    }
}

