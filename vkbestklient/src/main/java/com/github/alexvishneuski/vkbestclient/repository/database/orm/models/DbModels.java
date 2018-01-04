package com.github.alexvishneuski.vkbestclient.repository.database.orm.models;

import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.MessagesTableModel;
import com.github.alexvishneuski.vkbestclient.repository.database.tablemodel.UsersTableModel;

public class DbModels {

    public static final Class<?>[] DB_MODELS =
            new Class[]{
                    UsersTableModel.class, MessagesTableModel.class
            };
}
