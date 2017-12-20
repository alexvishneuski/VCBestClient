package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarDialogsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarNotificationsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.RecyclerViewDialogsFragment;

import java.util.ArrayList;
import java.util.List;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, +adapter in separate classes
* 4. arrive round avatars
*/

public class SharedActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private int mRecyclerViewFrameContainer;

    private View mToNewsImageButton;
    private View mToSearchImageButton;
    private View mToDialogsImageButton;
    private View mToNotificationsImageButton;

    private List<Pair<Integer, ? extends Fragment>> mPairs;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        //setContentView(R.layout.activity_shared);
        setContentView(R.layout.activity_shared);

        initFragments();

        initNavigationBarButtons();

    }


    /*find top bar container end show there top bar fragment*/
    private void initFragments() {
        Log.d(TAG, "initFragments");
/*from*/
        mTopBarFrameContainer = R.id.top_bar_frame_container;
        mRecyclerViewFrameContainer = R.id.container_recycler_view;

        initEntryPointFragments();


        /*to*/

        /* initTopBarFragment();

        initRecyclerViewFragment();*/
    }


    /*show all necessary fragments on this activity*/
    public void replaceAllFragments(List<Pair<Integer, ? extends Fragment>> pPairList) {
        Log.d(TAG, "replaceAllFragments");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        for (Pair<Integer, ? extends Fragment> pair : pPairList
                ) {
            int frameContainer = pair.first;
            Fragment fragment = pair.second;
            transaction.replace(frameContainer, fragment);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*show any fragment on this activity*/
    public void replaceFragment(int frameContainer, Fragment fragment) {
        Log.d(TAG, "replaceFragment");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*
    //TODO resolve, what is the entry point?
    *//*find top bar container and show top bar fragment
    * for first time - dialogsfragment*//*
    private void initTopBarFragment() {
        Log.d(TAG, "initTopBarFragment");
        mTopBarFrameContainer = R.id.top_bar_frame_container;
        replaceFragment(mTopBarFrameContainer, new TopBarDialogsFragment());
    }


    private void initRecyclerViewFragment() {
        Log.d(TAG, "initRecyclerViewFragment");
        mRecyclerViewFrameContainer = R.id.container_recycler_view;
        replaceFragment(mRecyclerViewFrameContainer, new RecyclerViewDialogsFragment());
    }

    */


    private void initNavigationBarButtons() {
        Log.d(TAG, "initNavigationBarButtons called ");

        mToNewsImageButton = findViewById(R.id.news_image_button);

        mToSearchImageButton = findViewById(R.id.search_image_button);

        mToDialogsImageButton = findViewById(R.id.messages_image_button);
        setToDialogsListener();

        mToNotificationsImageButton = findViewById(R.id.notifications_image_button);
        setToNotificationsListener();
    }


    //todo change 2. par to notificationFragment
    private void setToNotificationsListener() {
        Log.d(TAG, "setToNotificationsListener called");
        mToNotificationsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(mTopBarFrameContainer, new TopBarNotificationsFragment());
                replaceFragment(mRecyclerViewFrameContainer, new RecyclerViewDialogsFragment());
            }
        });
    }

    private void setToDialogsListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToDialogsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPairs = getPairsForDialogInitialisation();
                replaceAllFragments(mPairs);
            }
        });
    }
    //TODO must be next logic: if doesn't exist saved state - default view (profile, dialogs.)
    //TODO (continue) If it exists - to this view and prefer to this point in view
    private void initEntryPointFragments() {
        mPairs = getPairsForDialogInitialisation();
        replaceAllFragments(mPairs);
    }

    @NonNull
    private List<Pair<Integer, ? extends Fragment>> getPairsForDialogInitialisation() {
        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarDialogsFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new RecyclerViewDialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    @NonNull
    private List<Pair<Integer, ? extends Fragment>> getPairsForNotificationInitialisation() {
        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarNotificationsFragment());
        //todo change 2. par to notificationFragment
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new RecyclerViewDialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }
}
