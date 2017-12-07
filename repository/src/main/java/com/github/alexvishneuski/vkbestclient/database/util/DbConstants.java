package com.github.alexvishneuski.vkbestclient.database.util;

public class DbConstants {

    public interface DataTypes {

        String TEXT_TYPE = " TEXT";
        String BLOB_TYPE = " BLOB";
        String INTEGER_TYPE = " INTEGER";
        String REAL_TYPE = " REAL";
        String NUMERIC_TYPE = " NUMERIC";
        String NULL_TYPE = " NULL";


        String COMMA_SEP = ",";

    }

    public interface MessagesTable {

        String TABLE_NAME = "messages";

        String ID = "_id";
        String TITLE = "title";
        String BODY = "body";
        String AUTHOR_ID = "author_id";
        String RECEIVER_ID = "receiver_id";
        String SENDING_DATE = "sending_date";
        String READ_STATUS = "read_status";
    }

    public interface UsersTable {

        String TABLE_NAME = "users";

        String ID = "_id";
        String FIRST_NAME = "_id";
        //String LAST_NAME = "_id";
        String AVATAR_PATH = "title";
    }
}
