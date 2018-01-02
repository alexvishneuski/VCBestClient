package com.github.alexvishneuski.vkbestclient.repository.database.sql;

import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessagesTableModel;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UsersTableModel;

import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.CREATE_TABLE_IF_NOT_EXISTS;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.INSERT_INTO;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.PRIMARY_KEY;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.CLS_BRACKET;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.COMMA;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.OPN_BRACKET;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Types.INTEGER_TYPE;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Types.TEXT_TYPE;


public class Tables {

    public static final String CREATE_USERS_TABLE
            = CREATE_TABLE_IF_NOT_EXISTS +
            UsersTableModel.TABLE_NAME + OPN_BRACKET +
            UsersTableModel.ID + INTEGER_TYPE + PRIMARY_KEY + COMMA +
            UsersTableModel.FIRST_NAME + TEXT_TYPE + COMMA +
            UsersTableModel.LAST_NAME + TEXT_TYPE + COMMA +
            UsersTableModel.AVATAR_PATH + TEXT_TYPE + CLS_BRACKET;

    public static final String CREATE_MESSAGES_TABLE
            = CREATE_TABLE_IF_NOT_EXISTS +
            MessagesTableModel.TABLE_NAME + OPN_BRACKET +
            MessagesTableModel.ID + INTEGER_TYPE + PRIMARY_KEY + COMMA +
            MessagesTableModel.AUTHOR_ID + INTEGER_TYPE + COMMA +
            MessagesTableModel.RECIPIENT_ID + INTEGER_TYPE + COMMA +
            MessagesTableModel.TITLE + TEXT_TYPE + COMMA +
            MessagesTableModel.BODY + TEXT_TYPE + COMMA +
            MessagesTableModel.CREATED + INTEGER_TYPE + COMMA +
            MessagesTableModel.IS_READ + INTEGER_TYPE + CLS_BRACKET;


    public static final String INSERT_TEST_USER =
            INSERT_INTO + UsersTableModel.TABLE_NAME + OPN_BRACKET +
                    UsersTableModel.FIRST_NAME + COMMA + UsersTableModel.LAST_NAME + COMMA + UsersTableModel.AVATAR_PATH + CLS_BRACKET +
                    " VALUES (?,?)";
}
