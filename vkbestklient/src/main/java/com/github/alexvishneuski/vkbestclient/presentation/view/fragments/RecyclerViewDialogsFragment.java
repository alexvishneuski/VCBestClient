package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.datamodel.MessageDirection;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.UserInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

//TODO rename class
//TODO remove comments
public class RecyclerViewDialogsFragment extends Fragment {

    /*id of container in activity*/
    private int mDialogsLayoutId;

    /*view of this fragment*/
    private View mView;


    public static final String TEST_VIEW_URL = "https://pp.userapi.com/c627921/v627921671/289ec/CTenEfmZ2Rw.jpg";

    public final String TAG = this.getClass().getSimpleName();

    private List<Message> mMessages;
    private List<MessageInDialogListViewModel> mMessagesUI;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;

    private GetMessagesInDialogListAsyncTask mGetMessagesAsyncTasc;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        createRecyclerView(mView);
        setLayoutManagerToRecyclerView();

        startLoadMessages();
        getLoadingsResult();

        convertMessagesFromDomainToUIModel();

        createAdapter();
        setAdapterToView();

        return mView;
    }


    private void initView(LayoutInflater inflater) {
    /*find id*/
        mDialogsLayoutId = R.layout.fragment_recycler_view;
        /*create view*/
        mView = inflater.inflate(mDialogsLayoutId, null);
    }

    /*create recycler view*/
    private void createRecyclerView(View pView) {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = pView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
    }

    private void setLayoutManagerToRecyclerView() {
        Log.d(TAG, "setLayoutManagerToRecyclerView called");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void executeGetMessagesInDialogListAsyncTasc() {
        Log.d(TAG, "executeGetMessagesInDialogListAsyncTasc: called");
        mGetMessagesAsyncTasc = new GetMessagesInDialogListAsyncTask();
        mGetMessagesAsyncTasc.execute();
    }

    private void startLoadMessages() {
        Log.d(TAG, "startLoadMessages called");
        mMessagesUI = new ArrayList<>();

        executeGetMessagesInDialogListAsyncTasc();

        //TODO getting reasult from async tasc and convert to UI model, add getting users AsyncTask

        /*
        UIModel
       ======================
        private Long mId;
        private UserInDialogListViewModel mCurrentUser;
        private UserInDialogListViewModel mContactUser;
        private MessageDirectionViewModel mMessageDirection;
        private String mMessageSendingDate;
        private String mMessageTitle;
        private String mMessageBody;
        private boolean mIsMessageRead;

        steps in presentation
       =======================
        1. get messagesInDialogList from interactor

        2. convert from datamodel to UI model

        3. return UI MOdel
        */


       /*

        steps in interactor
       =======================

       1. get data from Repository
            AsyncTaks:
            1.1 AT for currentUser(name, avatar)
            getCurrentUser().getAvatarUrl()

            1.2. AT get messagesInDialog

            1.3. AT for each contactUser(name, avatar)
            getConactUSer();

       2. build datamodel

       3. return datamodel(public methods, methods return repo-models should be private/protected)


       steps in repository
       =======================

        1. executing interactor's requests

        ??? 2. convert every result to datamodel
       */

    }

    private void getLoadingsResult() {
        Log.d(TAG, "getLoadingsResult: called");

        try {
            Log.d(TAG, "onCreateView: getting result from AsyncTasc");
            //TODO run asynctask like async task. It work in UI thread
            mMessages = mGetMessagesAsyncTasc.get();
            Log.d(TAG, "onCreateView: got result from AsyncTasc: returned" + mMessages.size());
        } catch (InterruptedException pE) {
            pE.printStackTrace();
        } catch (ExecutionException pE) {
            pE.printStackTrace();
        }
    }

    private void convertMessagesFromDomainToUIModel() {
        Log.d(TAG, "convertMessagesFromDomainToUIModel: called");
        mMessagesUI = new ArrayList<>();

        for (Message message : mMessages
                ) {
            mMessagesUI.add(new MessageInDialogListViewModel(
                    //todo to think if to apply setters or Builder instead constructor
                    //todo change to real data
                    new UserInDialogListViewModel("CurrentUserName", TEST_VIEW_URL),
                    new UserInDialogListViewModel("ContactUserName", TEST_VIEW_URL),

                    convertUnixtimeToString(message.getMessageSendingDate(), Constants.DateFormat.PATTERN_DD_MM),
                    message.getMessageBody(),
                    (MessageDirection.INCOMING == message.getMessageDirection() ? MessageDirectionViewModel.INCOMING : MessageDirectionViewModel.OUTGOING),
                    message.isMessageRead()
            ));
        }
    }

    //todo extract to separate class
    private String convertUnixtimeToString(int pDateLong, String pattern) {

        Date date = new Date(pDateLong * 1000L);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateText = format.format(date);

        return dateText;
    }

    /*create adapter and send him messageList*/
    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mAdapter = new MessageInDialogListRecyclerAdapter(mMessagesUI);
    }

    private void setAdapterToView() {
        Log.d(TAG, "setAdapterToView called ");
        mRecyclerView.setAdapter(mAdapter);
    }

    private static class GetMessagesInDialogListAsyncTask extends AsyncTask<Void, Void, List<Message>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAT";

        @Override
        protected List<Message> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<Message> messages = new ArrayList<>();

            IDialogInteractor mDialogInteractor = new DialogInteractorImpl();
            messages.addAll(mDialogInteractor.getMessagesForDialogList());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start messageList print");
            System.out.println("printed " + messages.size() + " messages");
            System.out.println(messages);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish messageList print");

            return messages;
        }
    }
}
