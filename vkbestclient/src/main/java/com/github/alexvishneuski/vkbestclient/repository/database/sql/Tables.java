package com.github.alexvishneuski.vkbestclient.repository.database.sql;

import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessageDb;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UserDb;

import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.CREATE_TABLE_IF_NOT_EXISTS;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.INSERT_INTO;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.NOT_NULL;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.PRIMARY_KEY;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.RegEx.UNIQUE;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.CLS_BRACKET;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.COMMA;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Separators.OPN_BRACKET;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Types.INTEGER_TYPE;
import static com.github.alexvishneuski.vkbestclient.repository.database.util.Constants.Types.TEXT_TYPE;

/**
 * https://www.sqlite.org/datatype3.html
 * <p>
 * NULL. The value is a NULL value.
 * INTEGER. The value is a signed integer, stored in 1, 2, 3, 4, 6, or 8 bytes depending on the magnitude of the value.
 * REAL. The value is a floating point value, stored as an 8-byte IEEE floating point number.
 * TEXT. The value is a text string, stored using the database encoding (UTF-8, UTF-16BE or UTF-16LE).
 * BLOB. The value is a blob of data, stored exactly as it was input.
 * <p>
 * SQLite are capable of storing dates and times as TEXT, REAL, or INTEGER values:
 * <p>
 * TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
 * REAL as Julian day numbers, the number of days since noon in Greenwich on November 24, 4714 B.C. according to the proleptic Gregorian calendar.
 * INTEGER as Unix Time, the number of seconds since 1970-01-01 00:00:00 UTC.
 */

public class Tables {

    public static final String CREATE_USERS_TABLE
            = CREATE_TABLE_IF_NOT_EXISTS +
            UserDb.TABLE_NAME + OPN_BRACKET +
            UserDb._ID + INTEGER_TYPE + PRIMARY_KEY + UNIQUE + NOT_NULL + COMMA +
            UserDb.FIRST_NAME + TEXT_TYPE + COMMA +
            UserDb.LAST_NAME + TEXT_TYPE + COMMA +
            UserDb.AVATAR_PATH + TEXT_TYPE +
            CLS_BRACKET;

    public static final String CREATE_MESSAGES_TABLE
            = CREATE_TABLE_IF_NOT_EXISTS +
            MessageDb.TABLE_NAME + OPN_BRACKET +
            MessageDb._ID + INTEGER_TYPE + PRIMARY_KEY + UNIQUE + NOT_NULL + COMMA +
            MessageDb.AUTHOR_ID + INTEGER_TYPE + COMMA +
            MessageDb.RECIPIENT_ID + INTEGER_TYPE + COMMA +
            MessageDb.TITLE + TEXT_TYPE + COMMA +
            MessageDb.BODY + TEXT_TYPE + COMMA +
            MessageDb.CREATED + INTEGER_TYPE + COMMA +
            MessageDb.IS_READ + INTEGER_TYPE +
            CLS_BRACKET;

    public static final String INSERT_USER =
            INSERT_INTO + UserDb.TABLE_NAME + OPN_BRACKET +
                    UserDb._ID + COMMA +
                    UserDb.FIRST_NAME + COMMA +
                    UserDb.LAST_NAME + COMMA +
                    UserDb.AVATAR_PATH +
                    CLS_BRACKET +
                    " VALUES (?,?,?,?)";


}
