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
import com.github.alexvishneuski.vkbestclient.interactor.impl.DialogInteractorImpl;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.RecyclerViewDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.SharedActivity;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();


    private Button mToDialogsBasedRecyclerViewButton;

    private Button mToSharedActivityViewButton;

    private static IDialogInteractor mDialogInteractor;

    private GetDialogListAsStringAsyncTasc mGetDialogAsStringAsyncTasc;

    GetDialogListAsyncTasc mGetDialogListAsyncTasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        /*creating buttons*/
        initButtons();

        /*getting dialog List as String*/
        //  executeGetDialogListAsStringAsyncTasc();

        /*getting dialog List*/
        executeGetDialogListAsyncTasc();


    }


    private void executeGetDialogListAsyncTasc() {
        Log.d(TAG, "executeGetDialogListAsyncTasc: called");
        mGetDialogListAsyncTasc = new GetDialogListAsyncTasc();
        mGetDialogListAsyncTasc.execute();
    }

    private void executeGetDialogListAsStringAsyncTasc() {
        Log.d(TAG, "executeGetDialogListAsStringAsyncTasc: called");
        mGetDialogAsStringAsyncTasc = new GetDialogListAsStringAsyncTasc();
        mGetDialogAsStringAsyncTasc.execute();
    }

    private static class GetDialogListAsStringAsyncTasc extends AsyncTask<Void, Void, String> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAsStringAT";

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

    private static class GetDialogListAsyncTasc extends AsyncTask<Void, Void, List<VKApiDialog>> {

        private static final String ASYNC_TASK_TAG = "GetDialogListAsStringAT";

        @Override
        protected List<VKApiDialog> doInBackground(Void... voids) {
            Log.d(ASYNC_TASK_TAG, "doInBackground: called");

            List<VKApiDialog> dialogs = new ArrayList<>();

            mDialogInteractor = new DialogInteractorImpl();
            dialogs.addAll(mDialogInteractor.getDialogList());

            Log.d(ASYNC_TASK_TAG, "doInBackground: start dialogList print");
            System.out.println(dialogs);
            Log.d(ASYNC_TASK_TAG, "doInBackground: finish dialogList print");

            return dialogs;
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
