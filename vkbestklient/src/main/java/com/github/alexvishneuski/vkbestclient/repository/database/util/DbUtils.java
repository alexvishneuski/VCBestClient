package com.github.alexvishneuski.vkbestclient.repository.database.util;

public class DbUtils {

    public static String getTableName(Class<?> clazz) {

        return clazz.getSimpleName();
    }
}
