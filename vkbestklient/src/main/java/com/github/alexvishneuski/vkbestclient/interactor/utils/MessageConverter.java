package com.github.alexvishneuski.vkbestclient.interactor.utils;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;

public class MessageConverter {

    private static final String TAG = "MessageConverter";

    public static MessageDbModel convertMsgFromDomainToDb(MessageInDialogs msg) {
        Log.d(TAG, "convertMsgFromDomainToDb() called with: msg = [" + msg + "]");
        MessageDbModel dbMsg = new MessageDbModel();

        dbMsg = new MessageDbModel();
        dbMsg.setId(msg.getId());
        dbMsg.setAuthor_id(msg.getMessageDirection().equals(MessageDirection.OUTGOING) ? msg.getCurrentUser().getUserId() : msg.getContactUser().getUserId());
        dbMsg.setRecipient_id(msg.getMessageDirection().equals(MessageDirection.INCOMING) ? msg.getCurrentUser().getUserId() : msg.getContactUser().getUserId());
        dbMsg.setMessageTitle(msg.getMessageTitle());
        dbMsg.setMessageBody(msg.getMessageBody());
        dbMsg.setMessageSendingDate(msg.getMessageSendingDate());
        dbMsg.setMessageRead(msg.isMessageRead() ? 1 : 0);

        return dbMsg;
    }
}
