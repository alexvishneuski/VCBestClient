package com.github.alexvishneuski.vkbestclient.repository.database.tablemodel;

import android.provider.BaseColumns;

import com.github.alexvishneuski.vkbestclient.repository.database.orm.annotations.dbTable;
import com.github.alexvishneuski.vkbestclient.repository.database.util.DbUtils;

@dbTable("UserDb")
public class UserDb implements BaseColumns {

    public static final String TABLE_NAME = DbUtils.getTableName(UserDb.class);

    //public static final String ID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String AVATAR_PATH = "avatar_path";
}
