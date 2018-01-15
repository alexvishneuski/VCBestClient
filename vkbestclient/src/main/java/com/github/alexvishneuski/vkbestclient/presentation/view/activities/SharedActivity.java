package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.DialogsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarDialogsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarNewsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarNotificationsFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarProfileFragment;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarSearchFragment;

import java.util.ArrayList;
import java.util.List;

public class SharedActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;
    private int mRecyclerViewFrameContainer;

    private View mToProfileImageButton;
    private View mToDialogsImageButton;
    private View mToNotificationsImageButton;
    private View mToNewsImageButton;
    private View mToSearchImageButton;

    private Fragment mCurrentTopBarFragment;

    private List<Pair<Integer, ? extends Fragment>> mPairs;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shared);

        initFragments();

        initNavigationBarButtons();
    }

    private void initFragments() {
        Log.d(TAG, "initFragments");

        mTopBarFrameContainer = R.id.top_bar_frame_container;
        mRecyclerViewFrameContainer = R.id.container_recycler_view;

        initEntryPointFragments();
    }


    /*show all necessary fragments on this activity*/
    public void replaceAllFragments(List<Pair<Integer, ? extends Fragment>> pPairList) {
        Log.d(TAG, "replaceAllFragments() called with: pPairList = [" + pPairList + "]");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        for (Pair<Integer, ? extends Fragment> pair : pPairList
                ) {
            int frameContainer = pair.first;
            Fragment fragment = pair.second;
            transaction.replace(frameContainer, fragment);
        }

        transaction.addToBackStack(null);
        transaction.commit();

        //setting link to current fragments
        setLinksToCurrentFragments();
    }

    private void initNavigationBarButtons() {
        Log.d(TAG, "initNavigationBarButtons called ");


        mToProfileImageButton = findViewById(R.id.profile_image_button);
        setToProfileListener();

        mToNewsImageButton = findViewById(R.id.news_image_button);
        setToNewsListener();

        mToSearchImageButton = findViewById(R.id.search_image_button);
        setToSearchListener();

        mToDialogsImageButton = findViewById(R.id.messages_image_button);
        setToDialogsListener();

        mToNotificationsImageButton = findViewById(R.id.notifications_image_button);
        setToNotificationsListener();
    }

    private void setToDialogsListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToDialogsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentTopBarFragment.getClass().equals(TopBarDialogsFragment.class)) {
                    //TODO if now I'm on this fragment, must nothing to do
                    mPairs = getPairsForDialogsInitialisation();
                    replaceAllFragments(mPairs);

                }
            }
        });
    }

    //todo change 2. part to notificationFragment
    private void setToNotificationsListener() {
        Log.d(TAG, "setToNotificationsListener called");
        mToNotificationsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentTopBarFragment.getClass().equals(TopBarNotificationsFragment.class)) {
                    mPairs = getPairsForNotificationInitialisation();
                    replaceAllFragments(mPairs);

                    //mToDialogsImageButton.setEnabled(true);
                    //mToDialogsImageButton.setSelected(true);
                }
            }
        });
    }

    private void setToNewsListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToNewsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentTopBarFragment.getClass().equals(TopBarNewsFragment.class)) {
                    mPairs = getPairsForNewsInitialisation();
                    replaceAllFragments(mPairs);
                }
            }
        });
    }

    private void setToSearchListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToSearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentTopBarFragment.getClass().equals(TopBarSearchFragment.class)) {
                    mPairs = getPairsForSearchInitialisation();
                    replaceAllFragments(mPairs);
                }
            }
        });
    }

    private void setToProfileListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if is called from this activity - skipped
                if (!mCurrentTopBarFragment.getClass().equals(TopBarProfileFragment.class)) {
                    goToProfileFragment();
                }
            }
        });
    }

    private void initEntryPointFragments() {
        mPairs = getPairsForDialogsInitialisation();
        replaceAllFragments(mPairs);
    }


    @NonNull
    private List<Pair<Integer, ? extends Fragment>> getPairsForDialogsInitialisation() {

        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarDialogsFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new DialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    @NonNull
    private List<Pair<Integer, ? extends Fragment>> getPairsForNotificationInitialisation() {

        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarNotificationsFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new DialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    private List<Pair<Integer, ? extends Fragment>> getPairsForNewsInitialisation() {

        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarNewsFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new DialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    private List<Pair<Integer, ? extends Fragment>> getPairsForSearchInitialisation() {

        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarSearchFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new DialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    private List<Pair<Integer, ? extends Fragment>> getPairsForProfileInitialisation() {

        List<Pair<Integer, ? extends Fragment>> pairs = new ArrayList<>();
        Pair<Integer, ? extends Fragment> pair1 = new Pair<>(mTopBarFrameContainer, new TopBarProfileFragment());
        Pair<Integer, ? extends Fragment> pair2 = new Pair<>(mRecyclerViewFrameContainer, new DialogsFragment());
        pairs.add(pair1);
        pairs.add(pair2);

        return pairs;
    }

    private void setLinksToCurrentFragments() {
        Log.d(TAG, "setLinksToCurrentFragments() called ");
        mCurrentTopBarFragment = mPairs.get(0).second;
        Fragment currentRecyclerFragment = mPairs.get(1).second;
        Log.d(TAG, "setLinksToCurrentFragments(): mCurrentTopBarFragment: "
                + mCurrentTopBarFragment.getClass().getSimpleName()
                + " mCurrentRecyclerFragment "
                + currentRecyclerFragment.getClass().getSimpleName());
    }

    public void goToProfileFragment() {
        Log.d(TAG, "goToProfileFragment: ");
        mPairs = getPairsForProfileInitialisation();
        replaceAllFragments(mPairs);
        Toast.makeText(this, " Gone to ToProfileFragment", Toast.LENGTH_SHORT).show();
    }


}
