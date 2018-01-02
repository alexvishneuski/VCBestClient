package com.github.alexvishneuski.vkbestclient.repository.database.sql;

import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessagesTableModel;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UsersTableModel;

import static com.github.alexvishneuski.vkbestclient.repository.database.util.DbConstants.DataTypes.INTEGER_TYPE;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.DbConstants.DataTypes.PRIMARY_KEY;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.DbConstants.DataTypes.TEXT_TYPE;


public class Tables {

    public static final String CREATE_USERS_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            UsersTableModel.TABLE_NAME + " (" +
            UsersTableModel.ID + INTEGER_TYPE + PRIMARY_KEY + ", " +
            UsersTableModel.FIRST_NAME + TEXT_TYPE + ", " +
            UsersTableModel.LAST_NAME + TEXT_TYPE + ", " +
            UsersTableModel.AVATAR_PATH + TEXT_TYPE + ")";

    public static final String CREATE_MESSAGES_TABLE
            = "CREATE TABLE IF NOT EXISTS " +
            MessagesTableModel.TABLE_NAME + "(" +
            MessagesTableModel.ID + INTEGER_TYPE + PRIMARY_KEY + ", " +
            MessagesTableModel.AUTHOR_ID + INTEGER_TYPE + ", " +
            MessagesTableModel.RECIPIENT_ID + INTEGER_TYPE + ", " +
            MessagesTableModel.TITLE + TEXT_TYPE + ", " +
            MessagesTableModel.BODY + TEXT_TYPE + ", " +
            MessagesTableModel.CREATED + INTEGER_TYPE + ", " +
            MessagesTableModel.IS_READ + INTEGER_TYPE + ")";


    public static final String INSERT_TEST_USER =
            "INSERT INTO " + UsersTableModel.TABLE_NAME + "(" +
                    UsersTableModel.FIRST_NAME + "," + UsersTableModel.AVATAR_PATH + ")" +
                    " VALUES (?,?)";
}
