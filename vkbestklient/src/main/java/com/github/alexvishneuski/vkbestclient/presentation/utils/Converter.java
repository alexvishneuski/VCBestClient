package com.github.alexvishneuski.vkbestclient.presentation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    //todo extract to separate class
    public static String convertUnixtimeToString(int pDateLong, String pattern) {

        Date date = new Date(pDateLong * 1000L);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateText = format.format(date);

        return dateText;
    }
}
