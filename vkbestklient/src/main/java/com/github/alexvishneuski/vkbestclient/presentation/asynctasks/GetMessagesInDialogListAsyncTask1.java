package com.github.alexvishneuski.vkbestclient.presentation.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Converter;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class GetMessagesInDialogListAsyncTask1 extends AsyncTask<Context, Void, List<Message>> {

    private static final String ASYNC_TASK_TAG = "GetDialogListAT";

    Context mContext;

    @Override
    protected List<Message> doInBackground(Context... pContexts) {
        Log.d(ASYNC_TASK_TAG, "doInBackground: called");

        mContext = null;

        mContext = pContexts[0];

        List<Message> messages = new ArrayList<>();

        IDialogInteractor mDialogInteractor = new DialogInteractorImpl();
        messages.addAll(mDialogInteractor.getMessagesForDialogList());

        Log.d(ASYNC_TASK_TAG, "doInBackground: start messageList print");
        System.out.println("printed " + messages.size() + " messages");
        System.out.println(messages);
        Log.d(ASYNC_TASK_TAG, "doInBackground: finish messageList print");

        return messages;
    }

    @Override
    protected void onPostExecute(List<Message> pMessages) {
        super.onPostExecute(pMessages);
        List<MessageInDialogListViewModel> viewModel = Converter.convertMessagesFromDomainToUIModel(pMessages);


    }
}
