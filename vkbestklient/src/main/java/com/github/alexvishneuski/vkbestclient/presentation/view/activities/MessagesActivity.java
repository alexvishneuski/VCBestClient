package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.interactor.IMessageInHistoryInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.MessageInHistoryInteractorImpl;
import com.github.alexvishneuski.vkbestclient.interactor.model.MessageInDialogs;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.DialogsHistoryRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Constants;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Converter;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public final String TAG = this.getClass().getSimpleName();

    public final int LOAD_MESSAGES_COUNT = 20;

    private int mRecyclerViewContainer;
    //  private View mView;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private DialogsHistoryRecyclerAdapter mAdapter;

    private int mMessagesInHistoryTotalCount;

    private int mContactUserId;

    int mVisibleItemCount;
    int mTotalItemCount;
    int mFirstVisibleItemPosition;
    private boolean mIsLoading = true;

    private SharedActivity mParentActivity;

    private IMessageInHistoryInteractor mMessageInHistoryInteractor = new MessageInHistoryInteractorImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);

        //initViews();

        createRecyclerView();
        setLayoutManagerToRecyclerView();
        createAdapter();
        setAdapterToView();

        getContactUserId();
        loadMessagesInHistoryTotalCount();
        loadMessagesFirstTime();

        //TODO ADD listeners
        //addOnScrollListener();
        //setOnScrollListener();

    }


    private void getContactUserId() {
        String key = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_ID;
        Intent intent = getIntent();
        mContactUserId = intent.getExtras().getInt(key);
    }

    private void createRecyclerView() {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = findViewById(R.id.dialogs_history_recycler_view);
        mRecyclerView.setHasFixedSize(false);
    }

    private void setLayoutManagerToRecyclerView() {
        Log.d(TAG, "setLayoutManagerToRecyclerView called");
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //make reverse of messages order
        mLayoutManager.setReverseLayout(true);
        //move head at the end of array
        mLayoutManager.setStackFromEnd(false);
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
        loadCountTask.execute(mContactUserId);
    }

    private void onCountLoaded(Integer pCount) {
        mMessagesInHistoryTotalCount = pCount;
    }

    private void loadMessagesFirstTime() {
        Log.d(TAG, "loadMessagesFirstTime called");
        LoadMessagesAT loadTask = new LoadMessagesAT();
        loadTask.execute(0, LOAD_MESSAGES_COUNT, mContactUserId);
    }

    public class LoadMessagesCountAT extends AsyncTask<Integer, Void, Integer> {

        private static final String ASYNC_TASK_TAG = "LoadMessagesCountAT";

        @Override
        protected Integer doInBackground(Integer... pArgs) {

            int userId = pArgs[0];

            Log.d(ASYNC_TASK_TAG, "doInBackground: called");
            Log.d(TAG, "doInBackground: invoked getMessagesInHistoryTotalCount() for contactUser with id = " + userId);
            int messagesCount = mMessageInHistoryInteractor.getMessagesInHistoryTotalCount(userId);
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
        Log.d(TAG, "onLoaded() called with: pMessages = [" + pMessages + "]");
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
            int limit = pArgs[1];
            int userId = pArgs[2];

            List<MessageInDialogs> msgs = mMessageInHistoryInteractor.getMessagesInHistoryFromRepo(offset, limit, userId);
            Log.d(ASYNC_TASK_TAG, "doInBackground: returned " + msgs.size() + " messages");

            return msgs;
        }

        @Override
        protected void onPostExecute(List<MessageInDialogs> pMessages) {
            super.onPostExecute(pMessages);
            onLoaded(pMessages);
        }
    }
}
