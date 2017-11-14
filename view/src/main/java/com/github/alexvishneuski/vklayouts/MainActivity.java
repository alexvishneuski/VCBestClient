package com.github.alexvishneuski.vklayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.alexvishneuski.datamodel.Domain;
import com.github.alexvishneuski.interactor.Test;

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
