package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.R;

public class VkAuthActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        /*creating messages view*/
        setContentView(R.layout.activity_auth);
        onSuccess();
    }

    private void onSuccess() {
        goToSharedActivity();
    }

    private void goToSharedActivity() {
        Intent intent = new Intent(this, SharedActivity.class);
        startActivity(intent);
    }
}
