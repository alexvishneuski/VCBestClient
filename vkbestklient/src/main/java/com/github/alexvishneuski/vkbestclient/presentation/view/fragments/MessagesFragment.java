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
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.interactor.impl.MessageInHistoryInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.DialogsHistoryRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    private View mView;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private DialogsHistoryRecyclerAdapter mAdapter;

    private int mMessagesInHistoryTotalCount;

    private IMessageInHistoryInteractor mMessageInHistoryInteractor = new MessageInHistoryInteractorImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        //getLinkToParentActivity();

        createRecyclerView(mView);

        setLayoutManagerToRecyclerView();

        createAdapter();

        setAdapterToView();

        loadMessagesInHistoryTotalCount();

        loadMessagesFirstTime();

        addOnScrollListener();

        return mView;
    }


    private void initView(LayoutInflater inflater) {

        int messagesLayoutId = R.layout.fragment_recycler_view;

        mView = inflater.inflate(messagesLayoutId, null);
    }

    private void createRecyclerView(View pView) {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = pView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(false);
    }

    private void setLayoutManagerToRecyclerView() {
        Log.d(TAG, "setLayoutManagerToRecyclerView called");
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mMessagesUI = new ArrayList<>();
        mAdapter = new DialogsHistoryRecyclerAdapter(mMessagesUI);

        //setOnClickListenerToAdapter();
    }

    private void setAdapterToView() {
        Log.d(TAG, "setAdapterToView called ");
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadMessagesInHistoryTotalCount() {
        LoadMessagesInHistoryCountAT loadCountTask = new LoadMessagesInHistoryCountAT();
        loadCountTask.execute();
    }

    private void onCountLoaded(Integer pCount) {
        mMessagesInHistoryTotalCount = pCount;
    }

    public class LoadMessagesInHistoryCountAT extends AsyncTask<Void, Void, Integer> {

        private static final String ASYNC_TASK_TAG = "LoadDialogsCountAT";

        @Override
        protected Integer doInBackground(Void... pVoids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");
            int dialogsCount = mMessageInHistoryInteractor.getDialogsTotalCount();
            Log.d(ASYNC_TASK_TAG, "doInBackground returned " + dialogsCount + " dialogs");

            return dialogsCount;
        }

        @Override
        protected void onPostExecute(Integer pCount) {
            super.onPostExecute(pCount);
            onCountLoaded(pCount);
        }
    }
}
