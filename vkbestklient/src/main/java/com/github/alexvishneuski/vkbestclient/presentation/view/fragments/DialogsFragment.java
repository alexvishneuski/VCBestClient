package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.datamodel.User;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.Converter;

import java.util.ArrayList;
import java.util.List;

//TODO add getting users AsyncTask

public class DialogsFragment extends Fragment {

    public final int LOAD_DIALOGS_COUNT = 20;

    public final String TAG = this.getClass().getSimpleName();

    private View mView;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private RecyclerView.OnScrollListener mOnScrollListener;
    int mVisibleItemCount;
    int mTotalItemCount;
    int mFirstVisibleItemPosition;
    private boolean mIsLoading = true;

    private IDialogInteractor mDialogInteractor = new DialogInteractorImpl();

    private LoadDialogsAT mLoadTask;

    private int mApiDialogsTotalCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");

        initView(inflater);

        createRecyclerView(mView);
        addDevider();
        setLayoutManagerToRecyclerView();
        createAdapter();
        setAdapterToView();

        loadDialogsTotalCount();
        loadMessagesFirstTime();

        addOnScrollListener();
        setOnScrollListener();

        return mView;
    }


    private void addOnScrollListener() {
        mOnScrollListener = new RecyclerView.OnScrollListener()

        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mVisibleItemCount = mLayoutManager.getChildCount();
                mTotalItemCount = mLayoutManager.getItemCount();
                mFirstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!mIsLoading) {
                    if ((mVisibleItemCount + mFirstVisibleItemPosition) >= mTotalItemCount) {
                        mIsLoading = true;

                        if (mLoadTask != null) {
                            mLoadTask = new LoadDialogsAT();
                        }
                        if (LOAD_DIALOGS_COUNT + mTotalItemCount <= mApiDialogsTotalCount) {
                            assert mLoadTask != null;
                            mLoadTask.execute(LOAD_DIALOGS_COUNT, mTotalItemCount);
                        } else {
                            assert mLoadTask != null;
                            mLoadTask.execute((mApiDialogsTotalCount - mTotalItemCount), mTotalItemCount);
                        }

                    }
                }

            }
        };
    }

    private void setOnScrollListener() {
        mRecyclerView.setOnScrollListener(mOnScrollListener);
    }

    private void addDevider() {
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
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
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void loadMessagesFirstTime() {
        Log.d(TAG, "loadMessagesFirstTime called");
        mLoadTask = new LoadDialogsAT();
        mLoadTask.execute(LOAD_DIALOGS_COUNT, 0);
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

    public void onLoaded(Pair<List<Message>, List<User>> pMessagesWithUsers) {
        //record this value before making any changes to the existing list
        int itemCount = mAdapter.getItemCount();
        //do not reinitialize an existing reference, instead  need to  act directly on the existing reference
        mMessagesUI.addAll(Converter.convertMessagesFromDomainToUIModel(pMessagesWithUsers));
        //notify adapter
        mAdapter.notifyItemRangeInserted(itemCount, pMessagesWithUsers.first.size());
        //mAdapter.notifyDataSetChanged();
        mIsLoading = false;
    }

    private void loadDialogsTotalCount() {
        LoadDialogsCountAT loadCountTask = new LoadDialogsCountAT();
        loadCountTask.execute();
    }

    private void onCountLoaded(Integer pCount) {
        mApiDialogsTotalCount = pCount;
    }

    //TODO to deliverance from static maybe using Threads?
    public class LoadDialogsAT extends AsyncTask<Integer, Void, Pair<List<Message>, List<User>>> {

        private static final String ASYNC_TASK_TAG = "LoadDialogsAT";

        @Override
        protected Pair<List<Message>, List<User>> doInBackground(Integer... pArgs) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            int count = pArgs[0];
            int offset = pArgs[1];

            Pair<List<Message>, List<User>> msgWithUsers = mDialogInteractor.getPreparedForUiMessages(count, offset);

                        /*List<Message> messages = new ArrayList<>();
            messages.addAll(mDialogInteractor.getMessagesForDialogList(count, offset));*/
            Log.d(ASYNC_TASK_TAG, "doInBackground: returned " + msgWithUsers.first.size() + " messages");

            return msgWithUsers;
        }

        @Override
        protected void onPostExecute(Pair<List<Message>, List<User>> pMessagesWithUsers) {
            super.onPostExecute(pMessagesWithUsers);
            onLoaded(pMessagesWithUsers);
        }
    }

    public class LoadDialogsCountAT extends AsyncTask<Void, Void, Integer> {

        private static final String ASYNC_TASK_TAG = "LoadDialogsCountAT";

        @Override
        protected Integer doInBackground(Void... pVoids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");
            int dialogsCount = mDialogInteractor.getDialogsTotalCount();
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
