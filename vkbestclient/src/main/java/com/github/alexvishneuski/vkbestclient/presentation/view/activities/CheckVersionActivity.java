package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.asynctasks.AppVersionChecker;
import com.github.alexvishneuski.vkbestclient.presentation.asynctasks.PseudoDownloadAT;

public class CheckVersionActivity extends AppCompatActivity {

    public static final String APP_SOURCE = BuildConfig.SHARE_URL;
    private View mUpdateAppLaterButton;
    private boolean isVersionOk = false;
    private AppVersionChecker mChecker;
    private PseudoDownloadAT mPseudoDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_version);
        initView();

        startVersionChecking();
    }

    private void initView() {
        View updateAppNowButton = findViewById(R.id.update_app_now_button);
        mUpdateAppLaterButton = findViewById(R.id.update_app_later_button);

        startVersionChecking();

        //pseudo download
        updateAppNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPseudoDownload = new PseudoDownloadAT();
                //noinspection unchecked
                mPseudoDownload.execute(new Pair<Context, String>(CheckVersionActivity.this, APP_SOURCE));
                goToVkAuthActivity();
            }
        });

        //go to CustomerActivity
        mUpdateAppLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToVkAuthActivity();
            }
        });
    }

    private void startVersionChecking() {

        Toast.makeText(CheckVersionActivity.this, R.string.start_version_checking, Toast.LENGTH_SHORT).show();
        final Thread checkVersion = new Thread(new Runnable() {
            @Override
            public void run() {

                mChecker = new AppVersionChecker(CheckVersionActivity.this);
                isVersionOk = mChecker.checkAppVersion();
            }
        });
        checkVersion.start();

        //waiting response from server
        try {
            checkVersion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(CheckVersionActivity.this, (isVersionOk) ? getString(R.string.app_version_ok) : getString(R.string.you_need_update), Toast.LENGTH_SHORT).show();


        //if client version is lower -> update later button inaktiv
        onVersionChecked();
    }

    private void onVersionChecked() {
        if (isVersionOk) {
            goToVkAuthActivity();
        } else {
            mUpdateAppLaterButton.setEnabled(false);
        }
    }

    private void goToVkAuthActivity() {
        Intent intent = new Intent(this, VkAuthActivity.class);
        startActivity(intent);
    }
}
