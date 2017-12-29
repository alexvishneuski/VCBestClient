package com.github.alexvishneuski.vkbestclient.presentation.utils;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Converter {

    public static List<MessageInDialogListViewModel> convertMessagesFromDomainToUIModel(List<MessageInDialogs> mMessages) {
        Log.d("DialogsFragment", "convertMessagesFromDomainToUIModel: called");
        List<MessageInDialogListViewModel> messagesUI = new ArrayList<>();

        for (MessageInDialogs message : mMessages
                ) {
            messagesUI.add(
                    new MessageInDialogListViewModel(
                            convertFromDomainToUIModel(message.getCurrentUser()),
                            convertFromDomainToUIModel(message.getContactUser()),

                            convertTimeForUi(message.getMessageSendingDate()),

                            message.getMessageBody(),
                            (MessageDirection.INCOMING == message.getMessageDirection() ? MessageDirectionViewModel.INCOMING : MessageDirectionViewModel.OUTGOING),
                            message.isMessageRead()
                    ));
        }
        return messagesUI;
    }

    private static String convertTimeForUi(int pDateLong) {

        Calendar now = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        Calendar msgTime = Calendar.getInstance();

        yesterday.add(Calendar.DAY_OF_YEAR, -1);
        msgTime.setTime(new Date(pDateLong * 1000L));

        boolean isSameDay = now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == msgTime.get(Calendar.DAY_OF_YEAR);

        System.out.println(now.get(Calendar.YEAR) + " and " + msgTime.get(Calendar.YEAR));

        boolean isYesterday = now.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                (now.get(Calendar.DAY_OF_YEAR)) == yesterday.get(Calendar.DAY_OF_YEAR);

        boolean isSameYear = now.get(Calendar.YEAR) == msgTime.get(Calendar.YEAR);

        if (isSameDay) {
            return convertUnixtimeToString(pDateLong, Constants.DateFormat.PATTERN_HH_mm);
        } else if (isYesterday) {
            return Constants.DateFormat.PATTERN_YESTERDAY;
        } else if (isSameYear) {
            return convertUnixtimeToString(pDateLong, Constants.DateFormat.PATTERN_dd_MM);
        } else
            return convertUnixtimeToString(pDateLong, Constants.DateFormat.PATTERN_dd_MM_yy);
    }

    private static String convertUnixtimeToString(int pDateLong, String pattern) {

        Date date = new Date(pDateLong * 1000L);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());

        return format.format(date);
    }

    private static UserInDialogListViewModel convertFromDomainToUIModel(UserInDialogs pUserDomain) {

        final String TEST_VIEW_URL = "https://pp.userapi.com/c627921/v627921671/289ec/CTenEfmZ2Rw.jpg";

        UserInDialogListViewModel userUI = new UserInDialogListViewModel();
        userUI.setUserId(pUserDomain.getUserId());
        userUI.setUserFullName(pUserDomain.getUserFullName());
        //todo change to real data
        userUI.setUserAvatarPath(TEST_VIEW_URL);

        return userUI;
    }
}
