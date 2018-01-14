package com.github.alexvishneuski.vkbestclient.repository.database.orm.models;

import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessageDb;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UserDb;

public class DbModels {

    public static final Class<?>[] DB_MODELS =
            new Class[]{
                    UserDb.class, MessageDb.class
            };
}
