package com.github.alexvishneuski.vkbestclient.repository.database.util;

import android.net.Uri;

import com.github.alexvishneuski.vkbestclient.repository.database.provider.CustomContentProvider;

public class DbUtils {

    public static String getTableName(Class<?> clazz) {

        return clazz.getSimpleName();
    }

    public static Uri getTableUri(final Class<?> clazz) {

        return Uri.parse(CustomContentProvider.PROVIDER_URI + "/" + getTableName(clazz));
    }
}
