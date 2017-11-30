package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.model.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.model.UserInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.MessagesTopBarFragment;
import com.github.alexvishneuski.vklayouts.R;

import java.util.ArrayList;
import java.util.List;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/


public class RecyclerViewDialogsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private List<MessageInDialogListViewModel> mMessageList;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        /*creating messages view*/
        setContentView(R.layout.activity_recycler_view_dialogs);
        initFragments();

        createRecyclerViewAndSetLayoutManager();
        loadDataToMessageList();
        createAdapter();

        /*set adapter to view*/
        mRecyclerView.setAdapter(mAdapter);
    }

    /*create recycler view set linearLayoutManager to him*/
    private void createRecyclerViewAndSetLayoutManager() {
        Log.d(TAG, "createRecyclerViewAndSetLayoutManager");
        mRecyclerView = findViewById(R.id.dialogs_container_recycler_view);

        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /*create adapter and send him messageList*/
    private void createAdapter() {
        Log.d(TAG, "createAdapter");
        mAdapter = new MessageInDialogListRecyclerAdapter(mMessageList);
    }

    /*find top bar container end show there top bar fragment*/
    private void initFragments() {
        Log.d(TAG, "initFragments");
        findTopBarContainer();
        showTopBarFragment();
    }

    /*show any fragment on this activity*/
    public void showFragment(int frameContainer, Fragment fragment) {
        Log.d(TAG, "showFragment");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    /*find top bar container for top bar fragment*/
    private void findTopBarContainer() {
        Log.d(TAG, "findTopBarContainer");
        mTopBarFrameContainer = R.id.top_bar_frame_container;

    }

    /*show top bar fragment in top bar container*/
    private void showTopBarFragment() {
        Log.d(TAG, "showTopBarFragment");
        showFragment(mTopBarFrameContainer, new MessagesTopBarFragment());

    }

    private void loadDataToMessageList() {
        Log.d(TAG, "loadDataToMessageList");
        mMessageList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mMessageList.add(new MessageInDialogListViewModel(new UserInDialogListViewModel("Contact user full name" + i, "http://www.avatars." + i), i + "." + i, "Message message  message message message message message message message message message message message message message message message message message message message message message message message message message message message message message message message" + i));
        }
    }
}
