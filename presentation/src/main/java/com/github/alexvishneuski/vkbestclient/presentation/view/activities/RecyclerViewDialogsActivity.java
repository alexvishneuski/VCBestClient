package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.presentation.model.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.utils.StubResourcesUtility;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.MessagesTopBarFragment;
import com.github.alexvishneuski.vklayouts.R;

import java.util.List;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/


public class RecyclerViewDialogsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private List<MessageInDialogListViewModel> mMessageList;

    private int mTopBarFrameContainer;

    private RecyclerView mRecyclerView;

    //TODO delete as quickly as possible!!!
    private StubResourcesUtility mResourcesStub;


    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_dialogs_based_list_view);

        /*find top bar container end show there top bar fragment*/
        findTopBarContainer();
        showTopBarFragment();

        /*find list view*/
        mRecyclerView = findViewById(R.id.dialogs_container_recycler_view);

        //TODO delete as quickly as possible!!!
        /*creatingResourcesStub*/
        mResourcesStub = StubResourcesUtility.getStubResourcesUtility(getApplicationContext());

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //TODO Load data, set adapter
       /* mMessageList = //

        mRecyclerView.setAdapter(new MessageRecyclerAdapter(mMessageList));


        *//*create adapter*//*
        lastMessageAdapter = new StudyDialogsListViewAdapter(this, mResourcesStub.getLastMessages());

        *//*set adapter to list*//*
        listView.setAdapter(lastMessageAdapter);*/

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

}
