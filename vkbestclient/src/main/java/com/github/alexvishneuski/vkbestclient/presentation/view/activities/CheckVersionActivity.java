package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.alexvishneuski.vkbestclient.R;

public class CheckVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_version);
        initView();

        goToAuthActivity();
    }

    private void initView() {
    }

    private void goToAuthActivity() {
        Intent intent = new Intent(this, VkAuthActivity.class);
        startActivity(intent);
    }
}
