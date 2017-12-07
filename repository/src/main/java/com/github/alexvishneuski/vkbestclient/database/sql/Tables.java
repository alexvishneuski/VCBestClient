package com.github.alexvishneuski.vkbestclient.database.sql;

import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.DataTypes.BLOB_TYPE;
import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.DataTypes.INTEGER_TYPE;
import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.DataTypes.REAL_TYPE;
import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.DataTypes.TEXT_TYPE;
import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.MessagesTable;
import static com.github.alexvishneuski.vkbestclient.database.util.DbConstants.UsersTable;

public class Tables {

    public static final String CREATE_USERS_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            UsersTable.TABLE_NAME + "(" +
            UsersTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            UsersTable.FIRST_NAME + TEXT_TYPE + "," +
            UsersTable.AVATAR_PATH + BLOB_TYPE + ")";

    public static final String CREATE_MESSAGES_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            MessagesTable.TABLE_NAME + "(" +
            MessagesTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            MessagesTable.TITLE + TEXT_TYPE + "," +
            MessagesTable.BODY + TEXT_TYPE + "," +
            MessagesTable.AUTHOR_ID + INTEGER_TYPE + "," +
            MessagesTable.RECEIVER_ID + INTEGER_TYPE + "," +
            MessagesTable.SENDING_DATE + REAL_TYPE + "," +
            MessagesTable.READ_STATUS + REAL_TYPE + ")";

    public static final String INSERT_TEST_USER =
            "INSERT INTO " + UsersTable.TABLE_NAME + "(" +
                    UsersTable.FIRST_NAME + "," + UsersTable.AVATAR_PATH + ")" +
                    " VALUES (?,?)";
}
