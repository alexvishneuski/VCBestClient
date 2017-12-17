package com.github.alexvishneuski.vkbestclient.presentation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.interactor.impl.UserInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.RecyclerViewDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.SharedActivity;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();


    private Button mToDialogsBasedRecyclerViewButton;

    private Button mToSharedActivityViewButton;

    private static IDialogInteractor mDialogInteractor;

    private static IUserInteractor mUserInteractor;

    private GetUsersAsyncTasc mGetUsersAsyncTasc;

    private GetDialogsAsyncTasc mGetDialogListAsyncTasc;

    private GetDialogsAsStringAsyncTasc mGetDialogAsStringAsyncTasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        /*creating buttons*/
        initButtons();

        /*getting dialog List as String*/
        //  executeGetDialogsAsStringAsyncTasc();

        /*getting dialog List*/
        executeGetDialogsAsyncTasc();

        /*getting users List*/
        executeGetUsersAsyncTasc();
    }


    private void executeGetDialogsAsyncTasc() {
        Log.d(TAG, "executeGetDialogsAsyncTasc: called");
        mGetDialogListAsyncTasc = new GetDialogsAsyncTasc();
        mGetDialogListAsyncTasc.execute();
    }

    private void executeGetUsersAsyncTasc() {
        Log.d(TAG, "executeGetUsersAsyncTasc: called");
        mGetUsersAsyncTasc = new GetUsersAsyncTasc();
        mGetUsersAsyncTasc.execute();
    }

    private void executeGetDialogsAsStringAsyncTasc() {
        Log.d(TAG, "executeGetDialogsAsStringAsyncTasc: called");
        mGetDialogAsStringAsyncTasc = new GetDialogsAsStringAsyncTasc();
        mGetDialogAsStringAsyncTasc.execute();
    }


    private static class GetDialogsAsyncTasc extends AsyncTask<Void, Void, List<VKApiDialog>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAT";

        @Override
        protected List<VKApiDialog> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<VKApiDialog> dialogs = new ArrayList<>();

            mDialogInteractor = new DialogInteractorImpl();
            dialogs.addAll(mDialogInteractor.getDialogs());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start dialogList print");
            System.out.println(dialogs);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish dialogList print");

            return dialogs;
        }
    }

    private static class GetUsersAsyncTasc extends AsyncTask<Void, Void, List<VKApiUser>> {

        private static final String ASYNC_TASK_TAG = "GetUsersAT";

        @Override
        protected List<VKApiUser> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<VKApiUser> users;

            mUserInteractor = new UserInteractorImpl();
            users  = mUserInteractor.getUsers();


            Log.d(ASYNC_TASK_TAG, "doInBackground: start userList print");
            System.out.println(users);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish userList print");

            return users;
        }
    }

    private static class GetDialogsAsStringAsyncTasc extends AsyncTask<Void, Void, String> {

        private static final String ASYNC_TASK_TAG = "GetDialogsAsStringAT";

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            mDialogInteractor = new DialogInteractorImpl();
            String result = mDialogInteractor.getResultAsString();

            Log.d(ASYNC_TASK_TAG, "doInBackground: start result print");
            System.out.println(result);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish result print");

            return result;
        }
    }

    private void initButtons() {
        Log.d(TAG, "initButtons");


        mToDialogsBasedRecyclerViewButton = findViewById(R.id.to_app_based_activities_button);
        initToDialogsBasedRecyclerViewButton();

        mToSharedActivityViewButton = findViewById(R.id.to_app_based_fragments_button);

        initToSharedActivityViewButton();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();

    }

    private void initToDialogsBasedRecyclerViewButton() {
        Log.d(TAG, "initToDialogsBasedRecyclerViewButton");
        mToDialogsBasedRecyclerViewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewDialogsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initToSharedActivityViewButton() {
        Log.d(TAG, "initToSharedActivityViewButton called");
        mToSharedActivityViewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SharedActivity.class);
                startActivity(intent);
            }
        });
    }
}
