package com.github.alexvishneuski.vkbestclient.presentation.model;

import com.github.alexvishneuski.vkbestclient.datamodel.Message;

import java.util.List;

public class DialogViewModel {

    List<Message> mMessageList;

    public List<Message> getMessageList() {
        return mMessageList;
    }

    public void setMessageList(List<Message> pMessageList) {
        mMessageList = pMessageList;
    }
}
