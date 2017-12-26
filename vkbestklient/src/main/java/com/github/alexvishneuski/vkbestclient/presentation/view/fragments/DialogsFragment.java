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
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Converter;

import java.util.ArrayList;
import java.util.List;

//TODO add getting users AsyncTask
//TODO remove comments
public class DialogsFragment extends Fragment {

    public static final int LOAD_DIALOGS_COUNT = 20;
    public static final int LOAD_DIALOGS_OFFSET = 0;

    public final String TAG = this.getClass().getSimpleName();

    private View mView;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;
    private int mItemCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        createRecyclerView(mView);
        setLayoutManagerToRecyclerView();
        createAdapter();
        setAdapterToView();

        startLoadMessages();

        return mView;
    }


    private void initView(LayoutInflater inflater) {

        int dialogsLayoutId = R.layout.fragment_recycler_view;

        mView = inflater.inflate(dialogsLayoutId, null);
    }

    private void createRecyclerView(View pView) {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = pView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
    }

    private void setLayoutManagerToRecyclerView() {
        Log.d(TAG, "setLayoutManagerToRecyclerView called");
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    private void startLoadMessages() {
        Log.d(TAG, "startLoadMessages called");
        GetMessagesInDialogListAsyncTask getMessagesAsyncTask = new GetMessagesInDialogListAsyncTask();
        getMessagesAsyncTask.execute(LOAD_DIALOGS_COUNT, LOAD_DIALOGS_OFFSET);
    }

    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mMessagesUI = new ArrayList<>();
        mAdapter = new MessageInDialogListRecyclerAdapter(mMessagesUI);
    }

    private void setAdapterToView() {
        Log.d(TAG, "setAdapterToView called ");
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onLoaded(List<Message> pMessages) {
        //record this value before making any changes to the existing list
        mItemCount = mAdapter.getItemCount();
        //do not reinitialize an existing reference, instead  need to  act directly on the existing reference
        mMessagesUI.addAll(Converter.convertMessagesFromDomainToUIModel(pMessages));
        //notify adapter
        mAdapter.notifyItemRangeInserted(mItemCount, pMessages.size());
        //mAdapter.notifyDataSetChanged();
    }


    //TODO to deliverance from static maybe using Threads?
    public class GetMessagesInDialogListAsyncTask extends AsyncTask<Integer, Void, List<Message>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAT";

        @Override
        protected List<Message> doInBackground(Integer... pArgs) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            int count = pArgs[0];
            int offset = pArgs[1];

            List<Message> messages = new ArrayList<>();

            IDialogInteractor mDialogInteractor = new DialogInteractorImpl();
            messages.addAll(mDialogInteractor.getMessagesForDialogList(count, offset));

            Log.d(ASYNC_TASK_TAG, "doInBackground: start messageList print");
            System.out.println("printed " + messages.size() + " messages");
            System.out.println(messages);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish messageList print");

            return messages;
        }

        @Override
        protected void onPostExecute(List<Message> pMessages) {
            super.onPostExecute(pMessages);

            onLoaded(pMessages);
        }
    }
}
