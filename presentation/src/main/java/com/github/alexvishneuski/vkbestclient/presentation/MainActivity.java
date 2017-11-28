package com.github.alexvishneuski.vkbestclient.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.alexvishneuski.vkbestclient.datamodel.DomainTest;
import com.github.alexvishneuski.vkbestclient.interactor.InteractorTest;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.StudyBasedListViewWithArrayAdapterDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.StudyBasedListViewWithBaseAdapterDialogsActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.StudyBasedListViewWithArrayListDialogsActivity;
import com.github.alexvishneuski.vklayouts.R;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private Button mToDialogsBasedListViewWithArrayListButton;

    private Button mToDialogsBasedListViewWithBasedapterAndViewHolderButton;

    private Button mToDialogsBasedListViewWithArrayAdapterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        invokeOutsideTiers();

        mToDialogsBasedListViewWithArrayListButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_array_list_as_adapter_button);
        initToMessagesActivityFirstButton();

        mToDialogsBasedListViewWithBasedapterAndViewHolderButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_base_adapter_and_view_holder_button);
        initToMessagesActivitySecondButton();

        /*TODO delete after testing*/
        mToDialogsBasedListViewWithArrayAdapterButton = (Button) findViewById(R.id.to_dialogs_activity_based_list_view_with_array_adapter_button);
        initToTestActivityButton();


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

    private void invokeOutsideTiers() {
        Log.d(TAG, "invokeOutsideTiers");
        InteractorTest interactorTest = new InteractorTest();
        System.out.println(interactorTest.getS());

        DomainTest domainTest = new DomainTest();
        domainTest.testPrint();

    }

    private void initToMessagesActivityFirstButton() {
        Log.d(TAG, "initToMessagesActivityFirstButton");
        mToDialogsBasedListViewWithArrayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithArrayListDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void initToMessagesActivitySecondButton() {
        Log.d(TAG, "initToMessagesActivitySecondButton");
        mToDialogsBasedListViewWithBasedapterAndViewHolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithBaseAdapterDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    /*TODO delete after testing*/
    private void initToTestActivityButton() {
        Log.d(TAG, "initToTestActivityButton");
        mToDialogsBasedListViewWithArrayAdapterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyBasedListViewWithArrayAdapterDialogsActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

}
