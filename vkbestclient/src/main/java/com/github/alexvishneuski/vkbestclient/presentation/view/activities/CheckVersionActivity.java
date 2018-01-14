package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class CheckVersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        goToAuthActivity();
    }

    private void goToAuthActivity() {
        Intent intent = new Intent(this, VkAuthActivity.class);
        startActivity(intent);
    }
}
