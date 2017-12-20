package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.datamodel.Message;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.interactor.impl.UserInteractorImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        executeGetUsersAsyncTasc();

        executeGetMessagesInDialogListAsyncTasc();

        goToAuthActivity();
    }

    private void goToAuthActivity() {

        Intent intent = new Intent(MainActivity.this, AuthActivity.class);

        startActivity(intent);
    }


    private void executeGetMessagesInDialogListAsyncTasc() {
        Log.d(TAG, "executeGetMessagesInDialogListAsyncTasc: called");
        GetMessagesInDialogListAsyncTasc getMessagesAsyncTasc = new GetMessagesInDialogListAsyncTasc();
        getMessagesAsyncTasc.execute();
    }


    private void executeGetUsersAsyncTasc() {
        Log.d(TAG, "executeGetUsersAsyncTasc: called");
        GetUsersAsyncTasc getUsersAsyncTasc = new GetUsersAsyncTasc();
        getUsersAsyncTasc.execute();
    }


    //TODO remove asynctasks out of activity
    private static class GetMessagesInDialogListAsyncTasc extends AsyncTask<Void, Void, List<Message>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAT";

        @Override
        protected List<Message> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<Message> messages = new ArrayList<>();

            IDialogInteractor dialogInteractor = new DialogInteractorImpl();
            messages.addAll(dialogInteractor.getMessagesForDialogList());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start messageList print");
            System.out.println("printed " + messages.size() + " messages");
            System.out.println(messages);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish messageList print");

            return messages;
        }
    }


    private static class GetUsersAsyncTasc extends AsyncTask<Void, Void, List<VKApiUser>> {

        private static final String ASYNC_TASK_TAG = "GetUsersAT";

        @Override
        protected List<VKApiUser> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<VKApiUser> users = new ArrayList<>();

            IUserInteractor userInteractor = new UserInteractorImpl();

            users = userInteractor.getUsers();
            System.out.println(users.size() + " users");

            //System.out.println("printed " + users.size() + " users");
            //users.addAll(mUserInteractor.getUsers());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start userList print");
            //System.out.println("printed " + users.size() + " users");
            System.out.println(users);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish userList print");

            return users;
        }
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


}
