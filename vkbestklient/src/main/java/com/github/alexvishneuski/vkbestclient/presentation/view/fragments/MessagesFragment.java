package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.content.Intent;
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
import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.MessageInHistoryInteractorImpl;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.DialogsHistoryRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Constants;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Converter;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.SharedActivity;

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    public final int LOAD_MESSAGES_COUNT = 20;

    private View mView;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private DialogsHistoryRecyclerAdapter mAdapter;

    private int mMessagesInHistoryTotalCount;
    private LoadMessagesAT mLoadTask;

    int mVisibleItemCount;
    int mTotalItemCount;
    int mFirstVisibleItemPosition;
    private boolean mIsLoading = true;

    private SharedActivity mParentActivity;

    private IMessageInHistoryInteractor mMessageInHistoryInteractor = new MessageInHistoryInteractorImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        getLinkToParentActivity();

        createRecyclerView(mView);

        setLayoutManagerToRecyclerView();

        createAdapter();

        setAdapterToView();

        loadMessagesInHistoryTotalCount();

        loadMessagesFirstTime();

        //TODO ADD listeners
        //addOnScrollListener();
        //setOnScrollListener();

        return mView;
    }

    private void getLinkToParentActivity() {
        if (getActivity() != null) {
            mParentActivity = (SharedActivity) getActivity();

        }
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
        LoadMessagesCountAT loadCountTask = new LoadMessagesCountAT();
        loadCountTask.execute();
    }

    private void onCountLoaded(Integer pCount) {
        mMessagesInHistoryTotalCount = pCount;
    }

    private void loadMessagesFirstTime() {
        Log.d(TAG, "loadMessagesFirstTime called");
        mLoadTask = new LoadMessagesAT();
        mLoadTask.execute(0, LOAD_MESSAGES_COUNT);
    }

    public class LoadMessagesCountAT extends AsyncTask<Void, Void, Integer> {

        private static final String ASYNC_TASK_TAG = "LoadMessagesCountAT";

        String key = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_ID;
        Intent intent = mParentActivity.getIntent();
        int contactUserId = intent.getExtras().getInt(key);

        @Override
        protected Integer doInBackground(Void... pVoids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");
            Log.d(TAG, "doInBackground: invoked getMessagesInHistoryTotalCount() for contactUser with id = " + contactUserId);
            int messagesCount = mMessageInHistoryInteractor.getMessagesInHistoryTotalCount(contactUserId);
            Log.d(ASYNC_TASK_TAG, "doInBackground returned " + messagesCount + " dialogs");

            return messagesCount;
        }

        @Override
        protected void onPostExecute(Integer pCount) {
            super.onPostExecute(pCount);
            onCountLoaded(pCount);
        }
    }

    public void onLoaded(List<MessageInDialogs> pMessages) {
        //record this value before making any changes to the existing list
        int itemCount = mAdapter.getItemCount();
        //do not reinitialize an existing reference, instead  need to  act directly on the existing reference
        mMessagesUI.addAll(Converter.convertMessagesFromDomainToUIModel(pMessages));
        //notify adapter
        mAdapter.notifyItemRangeInserted(itemCount, pMessages.size());
        //mAdapter.notifyDataSetChanged();
        mIsLoading = false;
    }

    public class LoadMessagesAT extends AsyncTask<Integer, Void, List<MessageInDialogs>> {

        private static final String ASYNC_TASK_TAG = "LoadMessagesAT";

        /**
         * @param pArgs [0]  - offset, [1] - count=limit
         */
        @Override
        protected List<MessageInDialogs> doInBackground(Integer... pArgs) {
            Log.d(ASYNC_TASK_TAG, "doInBackground() called with: pArgs = [" + pArgs + "]");

            int offset = pArgs[0];
            int count = pArgs[1];

            List<MessageInDialogs> msgs = mMessageInHistoryInteractor.getMessagesInHistoryFromRepo(offset, count);

            Log.d(ASYNC_TASK_TAG, "doInBackground: returned " + msgs.size() + " messages");

            for (MessageInDialogs mes : msgs
                    ) {
                System.out.println("!!!===============!!! " + mes.getContactUser());

            }
            return msgs;
        }

        @Override
        protected void onPostExecute(List<MessageInDialogs> pMessages) {
            super.onPostExecute(pMessages);
            onLoaded(pMessages);
        }
    }
}
