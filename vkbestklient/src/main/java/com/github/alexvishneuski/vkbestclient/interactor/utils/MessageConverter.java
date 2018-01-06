package com.github.alexvishneuski.vkbestclient.interactor.utils;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.model.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.database.dbmodel.MessageDbModel;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;

public class MessageConverter {

    private static final String TAG = "MessageConverter";

    //COWORKING DB AREA

    public static MessageDbModel convertMsgFromDomainToDb(MessageInDialogs pMsg) {
        Log.d(TAG, "convertMsgFromDomainToDb() called with: msg = [" + pMsg + "]");

        MessageDbModel dbMsg = new MessageDbModel();

        dbMsg.setId(pMsg.getId());
        dbMsg.setAuthor_id(pMsg.getMessageDirection().equals(MessageDirection.OUTGOING) ? pMsg.getCurrentUser().getUserId() : pMsg.getContactUser().getUserId());
        dbMsg.setRecipient_id(pMsg.getMessageDirection().equals(MessageDirection.INCOMING) ? pMsg.getCurrentUser().getUserId() : pMsg.getContactUser().getUserId());
        dbMsg.setMessageTitle(pMsg.getMessageTitle());
        dbMsg.setMessageBody(pMsg.getMessageBody());
        dbMsg.setMessageSendingDate(pMsg.getMessageSendingDate());
        dbMsg.setMessageRead(pMsg.isMessageRead() ? 1 : 0);

        return dbMsg;
    }

    //COWORKING VKAPI AREAAREA

    public static MessageInDialogs convertMsgFromVKApiToDomain(VKApiMessage pMsg, UserInDialogs pCurrentUser) {
        Log.d(TAG, "convertMsgFromVKApiToDomain() called with: pMsg = [" + pMsg + "], pCurrentUser = [" + pCurrentUser + "]");

        MessageInDialogs domainMessage = new MessageInDialogs();

        domainMessage.setCurrentUser(pCurrentUser);

        UserInDialogs contactUser = new UserInDialogs();
        contactUser.setUserId(pMsg.getContactUserId());
        domainMessage.setContactUser(contactUser);

        domainMessage.setId(pMsg.getId());
        domainMessage.setMessageDirection((pMsg.getDirection() == 0) ? MessageDirection.INCOMING : MessageDirection.OUTGOING);
        domainMessage.setMessageSendingDate(pMsg.getSendingDate());
        domainMessage.setMessageTitle(pMsg.getTitle());
        domainMessage.setMessageBody(pMsg.getBody());
        domainMessage.setMessageRead((pMsg.getReadStatus() == 0) ? false : true);

        return domainMessage;
    }
}
