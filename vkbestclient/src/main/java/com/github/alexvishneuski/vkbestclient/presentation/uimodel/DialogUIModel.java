package com.github.alexvishneuski.vkbestclient.presentation.uimodel;

import java.util.List;

public class DialogUIModel {

    private  List<MessageInDialogListViewModel> mMessageList;

    public List<MessageInDialogListViewModel> getMessageList() {

        return mMessageList;
    }

    public void setMessageList(List<MessageInDialogListViewModel> pMessageList) {
        mMessageList = pMessageList;
    }
}