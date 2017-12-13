package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.NotificationsTopBarFragment;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, +adapter in separate classes
* 4. arrive round avatars
*/

public class RecyclerViewNotificationsActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private View mToNewsImageButton;
    private View mToSearchImageButton;
    private View mToDialogsImageButton;
    private View mToNotificationsImageButton;


    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_recycler_view_notifications);
        initFragments();


        initNavigationBarButtons();


    }

    private void initNavigationBarButtons() {
        Log.d(TAG, "initNavigationBarButtons called ");

        mToNewsImageButton = findViewById(R.id.news_image_button);

        mToSearchImageButton = findViewById(R.id.search_image_button);

        mToDialogsImageButton = findViewById(R.id.messages_image_button);
        setToDialogsListener();

        mToNotificationsImageButton = findViewById(R.id.notifications_image_button);
        setToNotificationsListener();
    }


    private void setToNotificationsListener() {
        Log.d(TAG, "setToNotificationsListener called");
        mToNotificationsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewNotificationsActivity.this, RecyclerViewNotificationsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setToDialogsListener() {
        Log.d(TAG, "setToDialogsListener called ");
        mToDialogsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewNotificationsActivity.this, RecyclerViewDialogsActivity.class);
                startActivity(intent);
            }
        });
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
        mTopBarFrameContainer = R.id.notifications_top_bar_frame_container;
    }

    /*show top bar fragment in top bar container*/
    private void showTopBarFragment() {
        Log.d(TAG, "showTopBarFragment");
        showFragment(mTopBarFrameContainer, new NotificationsTopBarFragment());
    }


}
