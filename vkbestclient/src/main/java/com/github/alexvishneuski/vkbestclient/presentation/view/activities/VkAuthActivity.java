package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;

import java.util.List;

/* TODO:
* 1. make ListView layout_height: mach_parent () by hide topbar panel
* 2. make TextView message_body layout_width : mach_parent by default of own image
* 3. extract asynctasc, adapter in separate classes
* 4. arrive round avatars
*/

public class VkAuthActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();


    private int mTopBarFrameContainer;

    private View mToNewsImageButton;
    private View mToSearchImageButton;
    private View mToDialogsImageButton;
    private View mToNotificationsImageButton;

    private List<MessageInDialogListViewModel> mMessageList;
    private RecyclerView mRecyclerView;
    private MessageInDialogListRecyclerAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);

        /*creating messages view*/
        setContentView(R.layout.activity_auth);


        onSuccess();
    }


    private void onSuccess() {
        goToSharedActivity();
    }

    private void goToSharedActivity() {
        Intent intent = new Intent(this, SharedActivity.class);
        startActivity(intent);
    }
}
