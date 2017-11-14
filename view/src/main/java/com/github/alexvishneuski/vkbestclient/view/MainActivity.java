package com.github.alexvishneuski.vkbestclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.alexvishneuski.vklayouts.R;
import com.github.alexvishneuski.vkbestclient.datamodel.Domain;
import com.github.alexvishneuski.vkbestclient.interactor.Test;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Test test = new Test();
        System.out.println(test.getS());

        Domain domain = new Domain();
        domain.testPrint();
    }
}
