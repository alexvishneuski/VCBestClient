package com.github.alexvishneuski.vkbestclient.repository.database.tablemodel;

import android.provider.BaseColumns;

import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbTable;

@dbTable("messages")
public class MessagesTableModel implements BaseColumns {

    public static final String TABLE_NAME = "messages";

    //public static final String ID = "_id";
    public static final String AUTHOR_ID = "author_id";
    public static final String RECIPIENT_ID = "recipient_id";
    public static final String CREATED = "created";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String IS_READ = "is_read";
}
