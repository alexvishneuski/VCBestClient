package com.github.alexvishneuski.vkbestclient.presentation.utils;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.datamodel.MessageDirection;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converter {

    //todo extract to separate class
    public static String convertUnixtimeToString(int pDateLong, String pattern) {

        Date date = new Date(pDateLong * 1000L);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateText = format.format(date);

        return dateText;
    }

    public static List<MessageInDialogListViewModel> convertMessagesFromDomainToUIModel(List<Message> mMessages) {
        Log.d("DialogsFragment", "convertMessagesFromDomainToUIModel: called");
        List<MessageInDialogListViewModel> messagesUI = new ArrayList<>();

        final String TEST_VIEW_URL = "https://pp.userapi.com/c627921/v627921671/289ec/CTenEfmZ2Rw.jpg";

        for (Message message : mMessages
                ) {
            messagesUI.add(new MessageInDialogListViewModel(
                    //todo to think if to apply setters or Builder instead constructor
                    //todo change to real data
                    new UserInDialogListViewModel("CurrentUserName", TEST_VIEW_URL),
                    new UserInDialogListViewModel("ContactUserName", TEST_VIEW_URL),

                    Converter.convertUnixtimeToString(message.getMessageSendingDate(), Constants.DateFormat.PATTERN_DD_MM),
                    message.getMessageBody(),
                    (MessageDirection.INCOMING == message.getMessageDirection() ? MessageDirectionViewModel.INCOMING : MessageDirectionViewModel.OUTGOING),
                    message.isMessageRead()
            ));
        }
        return messagesUI;
    }
}
