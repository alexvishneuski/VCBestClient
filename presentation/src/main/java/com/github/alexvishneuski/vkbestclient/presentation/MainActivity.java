package com.github.alexvishneuski.vkbestclient.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.alexvishneuski.vkbestclient.datamodel.DomainTest;
import com.github.alexvishneuski.vkbestclient.interactor.InteractorTest;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.BasedArrayAdapterListViewActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.LastMessagesActivity;
import com.github.alexvishneuski.vkbestclient.presentation.view.activities.MessagesActivity;
import com.github.alexvishneuski.vklayouts.R;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private Button mToMessagesFirstButton;

    private Button mToMessagesSecondButton;

    private Button mToTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_main);

        invokeOutsideTiers();

        mToMessagesFirstButton = (Button) findViewById(R.id.to_last_messages_activity_first_button);
        initToMessagesActivityFirstButton();

        mToMessagesSecondButton = (Button) findViewById(R.id.to_last_messages_activity_second_button);
        initToMessagesActivitySecondButton();

        /*TODO delete after testing*/
        mToTestButton = (Button) findViewById(R.id.to_test);
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
        mToMessagesFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void initToMessagesActivitySecondButton() {
        Log.d(TAG, "initToMessagesActivitySecondButton");
        mToMessagesSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LastMessagesActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    /*TODO delete after testing*/
    private void initToTestActivityButton() {
        Log.d(TAG, "initToTestActivityButton");
        mToTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasedArrayAdapterListViewActivity.class);
                //Case #1.2
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

}
