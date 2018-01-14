package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.imageloader.NOrda;
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

    private EditText mMessageInputEditText;
    private View mRecyclerArea;
    private View mAttachVoiceImageButton;
    private View mSendMessageButton;


    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private List<MessageInDialogListViewModel> mMessagesUI;
    private DialogsHistoryRecyclerAdapter mAdapter;

    private int mMessagesInHistoryTotalCount;

    private int mContactUserId;
    private String mContactUserAvatarPath;
    private String mContactUserName;
    private String mContactUserAdditional;

    private ImageView mContactUserAvatarImageView;
    private TextView mContactUserNameTextView;
    private TextView mContactUserAdditionalTextView;


    int mVisibleItemCount;
    int mTotalItemCount;
    int mFirstVisibleItemPosition;
    private boolean mIsLoading = true;

    private boolean mIsFocusable = false;

    private SharedActivity mParentActivity;

    private IMessageInHistoryInteractor mMessageInHistoryInteractor = new MessageInHistoryInteractorImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);

        findViews();
        getContactUserInfo();
        initViews();

        createRecyclerView();
        setLayoutManagerToRecyclerView();
        createAdapter();
        setAdapterToView();


        loadMessagesInHistoryTotalCount();
        loadMessagesFirstTime();

        //TODO ADD listeners
        //addOnScrollListener();
        //setOnScrollListener();

    }

    private void findViews() {
        mContactUserAvatarImageView = findViewById(R.id.histiry_contact_user_avatar_image_view);
        mContactUserAdditionalTextView = findViewById(R.id.history_contact_user_addition_info_text_view);
        mAttachVoiceImageButton = findViewById(R.id.history_attach_voice_image_view);
        mContactUserNameTextView = findViewById(R.id.history_contact_user_name_text_view);
    }

    private void initViews() {

        NOrda.INSTANCE.load(mContactUserAvatarPath).into(mContactUserAvatarImageView);

        mContactUserNameTextView.setText(mContactUserName);
        startAnimation(mContactUserNameTextView);

        mContactUserAdditionalTextView.setText(mContactUserAdditional);

        mMessageInputEditText = findViewById(R.id.history_messages_input_text_view);
        mMessageInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*mMessageInputEditText.requestFocus();*/
                mIsFocusable = true;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_HIDDEN);
            }
        });
/*
        mRecyclerArea = findViewById(R.id.history_area_recycler_view);
        mRecyclerArea.setClickable(true);
        mRecyclerArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("!!!!!!!!!!!!!!=====RV AREA============!!!!!!!!!!!!!!!!!!!");
                mIsFocusable = false;
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mRecyclerArea.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });*/

    }

    private void startAnimation(View pView) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_dialogs_history_name);
        pView.startAnimation(animation);
    }

    private void getContactUserInfo() {
        Log.d(TAG, "getContactUserInfo() called");

        String idKey = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_ID;
        String avatarPathKey = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_AVATAR;
        String nameKey = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_NAME;
        String additionalKey = Constants.IntentConstants.CONTACT_USER_FOR_DIALOG_HISTORY_ADDITIONAL_INFO;

        Intent intent = getIntent();

        mContactUserId = intent.getExtras().getInt(idKey);
        mContactUserAvatarPath = intent.getExtras().getString(avatarPathKey);
        mContactUserName = intent.getExtras().getString(nameKey);
        mContactUserAdditional = intent.getExtras().getString(additionalKey);
        System.out.println("recived data: "+mContactUserId+" "+mContactUserAvatarPath+" "+mContactUserName+" "+mContactUserAdditional);
    }

    private void createRecyclerView() {
        Log.d(TAG, "createRecyclerView");
        mRecyclerView = findViewById(R.id.history_recycler_view);
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
